package com.project.SpringCrud.Service;

import com.project.SpringCrud.DTO.UserGetterDTO;
import com.project.SpringCrud.DTO.UserSetterDTO;
import com.project.SpringCrud.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void addUser(UserSetterDTO user);

    List<User> getAllUsers();

    List<User> getAllCustomer();

    UserGetterDTO getUserByID(Long id);

    List<User> getAllEmployee();

    void updateUser(Long id, UserGetterDTO customer);

    void deleteUser(Long id);
}
