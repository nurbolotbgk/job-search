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

    private Long id;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 25, message = "Name length should be 2 to 25")
    private String name;
    @NotBlank(message = "Surname should not be empty")
    @Size(min = 2, max = 25, message = "SurName length should be 2 to 25")
    private String surname;
    @NotNull(message = "Age must be")
    @Min(value = 18, message = "Age must be minimum 18")
    @Max(value = 68, message = "Age must be maximum 68")
    private Integer age;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^\\+996\\d{9}$",
            message = "Phone number must start with +996 and then contains 9 digits"
    )
    private String phoneNumber;
    private String avatar;
    @NotNull(message = "Role_id is necessary")
    @Min(value = 1, message = "Role_id must be 1 - applicant or 2 - employer")
    @Max(value = 2, message = "Role_id must be 1 - applicant or 2 - employer")
    private Integer roleId;
}
