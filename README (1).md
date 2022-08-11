
# Spring Boot Crud Application using Role Based Authentication


There are 3 roles -->
* Admins --> can acesss all APIs
* Customer ---> can acesss only customer APIs
* Employee ---> can aceess only employee APIs








## GET STARTED

Download the project zip file and open the Spring Boot Project in your IDE, Eclipse or Intelli J (Recommended)


Create a database named "springcrud" in your MySQL databse using phpMyAdmin or SQLWorkbench
```
create schema springcrud;
```

Go to the application.properties file in the Spring Boot Project
and do the following

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/springcrud
spring.datasource.username=username   
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#your database address and database name goes here
#also give your password and username

```

After that just Run the Spring Boot project and it will create all the necessary tables in the database.




## API Reference

#### Login

```http
  POST /login
```
pass the following as form-data in Body

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. Your username |
| `password` | `string` | **Required**. password      |

#### Logout

```http
  GET /logout
```
logs out the logged in User.

#### Other APIs works based on roles

```http
  Post /add
```

Pass a user object as a JSON Object in Body

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `User Object` | `JSON Objecct` | **Required** |

Note: User role can vary and it can be added only through that API.

```
  Put /update
```

Pass a user object as a JSON Object in Body

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `User Object` | `JSON Objecct` | **Required** |
| `User Long Id` | `Path Variable` | **Required** |

Note: User role can vary and it can be updated only through that API.


```http
  Delete /delete
```

Pass a user object as a JSON Object in Body

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `User Long Id` | `Path Variable` | **Required** |

Note: User role can vary and it can be deleted only through that API.

```
  Get /getById
```

Pass a user object as a JSON Object in Body

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `User Long Id` | `Path Variable` | **Required** |

Note: User role can vary and it can fetched only through that API.

```
Get /getByRole
```
returns all users based on that role.
```
## Tech Stack

**Backend:** Spring Boot

**Database** : MySQL

