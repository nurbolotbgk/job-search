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

    @NotBlank(message = "{validation.vacancy.name.notblank}")
    @Size(min = 3, max = 100, message = "{validation.vacancy.name.size}")
    private String name;

    @NotBlank(message = "{validation.vacancy.description.notblank}")
    @Size(min = 5, max = 200, message = "{validation.vacancy.description.size}")
    private String description;

    @NotNull(message = "{validation.vacancy.category.notnull}")
    @Positive(message = "{validation.vacancy.category.positive}")
    private Integer categoryId;

    @Positive(message = "{validation.vacancy.salary.positive}")
    @Min(value = 500, message = "{validation.vacancy.salary.min}")
    @Max(value = 200000, message = "{validation.vacancy.salary.max}")
    private double salary;

    @Range(min = 0, max = 10, message = "{validation.vacancy.exp.from.range}")
    private Integer expFrom;

    @Min(value = 1, message = "{validation.vacancy.exp.to.min}")
    @Max(value = 120, message = "{validation.vacancy.exp.to.max}")
    private Integer expTo;

    @NotNull(message = "{validation.vacancy.active.notnull}")
    private Boolean active;

    @NotNull(message = "{validation.vacancy.user.notnull}")
    @Positive(message = "{validation.vacancy.user.positive}")
    private Long userId;

    @NotNull(message = "{validation.vacancy.created.notnull}")
    private LocalDateTime createdDate;

    private LocalDateTime updateTime;

    @Positive(message = "{validation.vacancy.id.positive}")
    private Long id;
}