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

    @NotBlank(message = "{validation.vacancy.name.notblank}")
    @Size(min = 3, max = 100, message = "{validation.vacancy.name.size}")
    private String name;

    @NotBlank(message = "{validation.vacancy.description.notblank}")
    @Size(min = 5, max = 200, message = "{validation.vacancy.description.size}")
    private String description;

    @NotNull(message = "{validation.vacancy.category.notnull}")
    @Positive(message = "{validation.vacancy.category.positive}")
    private Integer categoryId;

    @Min(value = 500, message = "{validation.vacancy.salary.min}")
    @Max(value = 200000, message = "{validation.vacancy.salary.max}")
    private double salary;

    @Min(value = 0, message = "{validation.vacancy.exp.from.min}")
    @Max(value = 10, message = "{validation.vacancy.exp.from.max}")
    private Integer expFrom;

    @Min(value = 1, message = "{validation.vacancy.exp.to.min}")
    @Max(value = 120, message = "{validation.vacancy.exp.to.max}" )
    private Integer expTo;

    private Boolean active;
}