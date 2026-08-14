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
public class VacancyFormDto {

    @NotBlank(message = "Name should not be empty")
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank(message = "Description should not be empty")
    @Size(min = 5, max = 200)
    private String description;

    @NotNull(message = "Category is required")
    @Positive
    private Integer categoryId;

    @Min(500)
    @Max(200000)
    private double salary;

    @Min(0)
    @Max(10)
    private Integer expFrom;

    @Min(1)
    @Max(120)
    private Integer expTo;

    private Boolean active;
}
