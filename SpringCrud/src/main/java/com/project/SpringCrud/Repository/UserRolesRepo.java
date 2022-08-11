package com.project.SpringCrud.Repository;

import com.project.SpringCrud.Model.User_Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRolesRepo extends JpaRepository<User_Roles,Long> {
    @Query(value = "SELECT * FROM `roles` WHERE role=:role LIMIT 1",nativeQuery = true)
    public User_Roles getRoleByName(@Param("role") String name);
}
