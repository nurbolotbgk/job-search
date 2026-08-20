package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeFormDto {

    @NotBlank(message = "Название резюме не должно быть пустым")
    @Size(min = 3, max = 100, message = "Название должно содержать от 3 до 100 символов")
    private String name;

    @NotNull(message = "Категория обязательна")
    @Positive(message = "Category ID должен быть положительным")
    private Integer categoryId;

    @NotNull(message = "Зарплата обязательна")
    @Positive(message = "Зарплата должна быть положительной")
    private Double salary;

    private Boolean active;
    private WorkExperienceInfoDto workExperience;
    @Valid
    private EducationInfoDto education;

    private ContactInfoDto contact;
}
