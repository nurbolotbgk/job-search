package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private String phone_number;
    private String avatar;
    private Integer role_id;
    private Integer id;
}
