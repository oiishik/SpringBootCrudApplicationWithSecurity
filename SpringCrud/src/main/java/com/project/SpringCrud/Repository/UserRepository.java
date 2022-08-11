package com.project.SpringCrud.Repository;

import com.project.SpringCrud.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = " SELECT * FROM `user` WHERE email=:x LIMIT 1",nativeQuery = true)
    public User getUserByEmail(@Param("x")String email);

    @Query(value = "SELECT * FROM `user` WHERE role=:x", nativeQuery = true)
    List<User> getByRole(@Param("x")String role);
}
