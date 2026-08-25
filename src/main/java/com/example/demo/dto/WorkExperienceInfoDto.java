package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceInfoDto {
    private Long id;

    @NotNull(message = "Количество лет обязательно")
    @Min(value = 0, message = "Количество лет не может быть отрицательным")
    private Integer years;

    @NotBlank(message = "Название компании обязательно")
    private String companyName;

    @NotBlank(message = "Должность обязательна")
    private String position;

    private String responsibilities;
}
