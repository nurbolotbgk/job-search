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
public class EditUserDto {

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

    @NotBlank(message = "{validation.user.phone.notblank}")
    @Pattern(regexp = "^\\+996\\d{9}$", message = "{validation.user.phone.pattern}")
    private String phoneNumber;
    private String avatar;
}
