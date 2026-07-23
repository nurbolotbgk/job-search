package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    @NotBlank(message = "Name should not be empty")
    @Size(min = 3, max = 100, message = "Name length should be 3 to 100")
    private String name;
    @NotBlank(message = "Description should not be empty")
    @Size(min = 5, max = 200, message = "Description length should be 5 to 200")
    private String description;
    @NotNull(message = "Category ID should not be null")
    @Positive(message = "Category ID should be positive")
    private Integer categoryId;
    @NotNull(message = "Salary should not be null")
    @Positive(message = "Salary should not be negative")
    @Min(value = 500, message = "Salary should be from 500")
    @Max(value = 200000, message = "Salary should be maximum 200000")
    private double salary;
    @Positive(message = "Expiration should not be negative")
    @Range(min = 0, max = 10, message = "Exp from should be from 0 to 10")
    private Integer expFrom;
    @Positive(message = "Expiration should not be negative")
    @Min(value = 1, message = "Exp should be from 1")
    @Max(value = 120, message = "Exp should be maximum 120")
    private Integer expTo;
    @NotNull(message = "The active field should be true or false")
    private Boolean active;
    @NotNull(message = "User ID should not be null")
    @Positive(message = "User ID should be positive")
    private Long userId;
    @NotNull(message = "Created date should not be null")
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    @Positive(message = "ID should be positive")
    private Long id;
}
