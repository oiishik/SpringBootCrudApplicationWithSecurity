package com.project.SpringCrud.Service.Implementation;

import com.project.SpringCrud.DTO.UserGetterDTO;
import com.project.SpringCrud.DTO.UserSetterDTO;
import com.project.SpringCrud.Model.User;
import com.project.SpringCrud.Model.User_Roles;
import com.project.SpringCrud.Repository.UserRepository;
import com.project.SpringCrud.Repository.UserRolesRepo;
import com.project.SpringCrud.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserRolesRepo userRolesRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.getUserByEmail(email);
        if(user==null)
        {
            throw new UsernameNotFoundException("Invalid Username");
        }
        return user;
    }

    @Override
    public void addUser(UserSetterDTO userDTO) {
        User user=new User();
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        Set<User_Roles> userRoles =new HashSet<>();
        for (String role: userDTO.getUserRoles()) {
            User_Roles UserRole=userRolesRepo.getRoleByName(role);
            userRoles.add(UserRole);
        }
        user.setUserRoles(userRoles);
        userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getAllCustomer() {
        return userRepo.getByRole("ROLE_CUSTOMER");
    }

    @Override
    public UserGetterDTO getUserByID(Long id) {
        User user = userRepo.getReferenceById(id);
        UserGetterDTO userDTO= new UserGetterDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    @Override
    public List<User> getAllEmployee() {
        return userRepo.getByRole("ROLE_EMPLOYEE");
    }

    @Override
    public void updateUser(Long id, UserGetterDTO userDTO) {
        User user= userRepo.getReferenceById(id);
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }


}
