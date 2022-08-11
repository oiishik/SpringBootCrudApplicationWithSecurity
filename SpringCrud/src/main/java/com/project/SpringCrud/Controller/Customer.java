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
@RequestMapping("/customer")
public class Customer {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @PostMapping("/add")
    public String addUser(@RequestBody UserSetterDTO user){
        if(!userService.getUserByID(user.getId()).getRole().equals("ROLE_CUSTOMER")){
            return "Can only Add Customer";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userService.addUser(user);
        }catch (Exception e){
            return "Couldn't add Customer";
        }
        return "Customer Added";
    }

    @PutMapping("/{id}/update")
    public String updateUser(@PathVariable("id")Long id,@RequestBody UserGetterDTO customer)
    {
        if(!userService.getUserByID(id).getRole().equals("ROLE_CUSTOMER")){
            return "Can only update Customer";
        }
        try {
            userService.updateUser(id,customer);
        }catch (Exception e){
            return "Error Updating Customer";
        }
        return "Customer Updated";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id")Long id){
        if(!userService.getUserByID(id).getRole().equals("ROLE_CUSTOMER")){
            return "Can only delete Customer";
        }
        try {
            userService.deleteUser(id);
        }catch (Exception e){
            return "Error Deleting Customer";
        }
        return "Customer Deleted";
    }

    @GetMapping("/get")
    public List<User> getAllCustomers(){
        try{
            return userService.getAllCustomer();
        }catch(Exception e){
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public UserGetterDTO getCustomerByID(@PathVariable("id")Long id){
        if(!userService.getUserByID(id).getRole().equals("ROLE_CUSTOMER")){
            return null;
        }
        try{
            return userService.getUserByID(id);
        }catch(Exception e){
            return null;
        }
    }
}
