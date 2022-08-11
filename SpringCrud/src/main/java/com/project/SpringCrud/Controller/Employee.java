package com.project.SpringCrud.Controller;

import com.project.SpringCrud.DTO.UserGetterDTO;
import com.project.SpringCrud.DTO.UserSetterDTO;
import com.project.SpringCrud.Model.User;
import com.project.SpringCrud.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "x")
@RestController
@RequestMapping("/employee")
public class Employee {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String addEmployee(@RequestBody UserSetterDTO user){
        if(!userService.getUserByID(user.getId()).getRole().equals("ROLE_EMPLOYEE")){
            return "Can only Update Employee";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userService.addUser(user);
        }catch (Exception e){
            return "Couldn't add Employee";
        }
        return "Employee Added";
    }

    @PutMapping("/{id}/update")
    public String updateEmployee(@PathVariable("id")Long id,@RequestBody UserGetterDTO employee)
    {
        if(!userService.getUserByID(id).getRole().equals("ROLE_EMPLOYEE")){
            return "Can only Update Employee";
        }
        try {
            userService.updateUser(id,employee);
        }catch (Exception e){
            return "Error Updating Employee";
        }
        return "Employee Updated";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable("id")Long id){
        if(!userService.getUserByID(id).getRole().equals("ROLE_CUSTOMER")){
            return "Can only Delete Customer";
        }
        try {
            userService.deleteUser(id);
        }catch (Exception e){
            return "Error Deleting Employee";
        }
        return "Employee Deleted";
    }

    @GetMapping("/get")
    public List<User> getEmployees(){
        try{
            return userService.getAllEmployee();
        }catch(Exception e){
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public UserGetterDTO getEmployeeByID(@PathVariable("id")Long id){
        if(!userService.getUserByID(id).getRole().equals("ROLE_EMPLOYEE")){
            return null;
        }
        try{
            return userService.getUserByID(id);
        }catch(Exception e){
            return null;
        }
    }
}
