package com.project.SpringCrud.Controller;

import com.project.SpringCrud.DTO.UserSetterDTO;
import com.project.SpringCrud.Model.User;
import com.project.SpringCrud.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "x")
@RestController
@RequestMapping("/admin")
public class Admin {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;


    @PostMapping("/add")
    public String addUser(@RequestBody UserSetterDTO user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userService.addUser(user);
        }catch (Exception e){
            return "Couldn't add User";
        }
        return "User Added";
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
