package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "{validation.user.name.notblank}")
    @Size(min = 2, max = 25, message = "{validation.user.name.size}")
    private String name;

    @NotBlank(message = "{validation.user.surname.notblank}")
    @Size(min = 2, max = 25, message = "{validation.user.surname.size}")
    private String surname;

    @NotNull(message = "{validation.user.age.notnull}")
    @Min(value = 18, message = "{validation.user.age.min}")
    @Max(value = 68, message = "{validation.user.age.max}")
    private Integer age;

    @NotBlank(message = "{validation.user.email.notblank}")
    @Email(message = "{validation.user.email.valid}")
    private String email;

    @NotBlank(message = "{validation.user.password.notblank}")
    @Size(min = 3, max = 24, message = "{validation.user.password.size}")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$", message = "{validation.user.password.pattern}")
    private String password;

    @NotBlank(message = "{validation.user.phone.notblank}")
    @Pattern(regexp = "^\\+996\\d{9}$", message = "{validation.user.phone.pattern}")
    private String phoneNumber;

    private String avatar;

    @NotNull(message = "{validation.user.role.notnull}")
    @Min(value = 1, message = "{validation.user.role.range}")
    @Max(value = 2, message = "{validation.user.role.range}")
    private Integer roleId;
}