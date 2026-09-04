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

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeFormDto {

    @NotBlank(message = "{validation.resume.name.notblank}")
    @Size(min = 3, max = 100, message = "{validation.resume.name.size}")
    private String name;

    @NotNull(message = "{validation.resume.category.notnull}")
    @Positive(message = "{validation.resume.category.positive}")
    private Integer categoryId;

    @NotNull(message = "{validation.resume.salary.notnull}")
    @Positive(message = "{validation.resume.salary.positive}")
    private Double salary;

    private Boolean active;

    @Valid
    @Builder.Default
    private List<WorkExperienceInfoDto> workExperiences = new ArrayList<>();

    @Valid
    @Builder.Default
    private List<EducationInfoDto> educations = new ArrayList<>();

    @Valid
    @Builder.Default
    private List<ContactInfoDto> contacts = new ArrayList<>();
}