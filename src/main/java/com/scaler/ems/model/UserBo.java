package com.scaler.ems.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserBo {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
