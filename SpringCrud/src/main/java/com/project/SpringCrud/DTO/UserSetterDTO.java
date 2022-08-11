package com.project.SpringCrud.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@Getter
@Setter
public class UserSetterDTO {
    private long id;
    private String email;
    private String password;
    private String role;
    private  Set<String> userRoles;


}
