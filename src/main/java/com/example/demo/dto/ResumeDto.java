package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {

    @NotNull(message = "{validation.resume.user.notnull}")
    @Positive(message = "{validation.resume.user.positive}")
    private Long userId;

    @NotBlank(message = "{validation.resume.name.notblank}")
    @Size(min = 3, max = 100, message = "{validation.resume.name.size}")
    private String name;

    @NotNull(message = "{validation.resume.category.notnull}")
    @Positive(message = "{validation.resume.category.positive}")
    private Integer categoryId;

    @NotNull(message = "{validation.resume.salary.notnull}")
    @Positive(message = "{validation.resume.salary.positive}")
    private Double salary;

    @NotNull(message = "{validation.resume.active.notnull}")
    private Boolean active;

    @NotNull(message = "{validation.resume.created.notnull}")
    private LocalDateTime createdDate;

    private LocalDateTime updateTime;

    @Positive(message = "{validation.resume.id.positive}")
    private Long id;

    private List<WorkExperienceInfoDto> workExperiences;
    private List<EducationInfoDto> educations;
    private List<ContactInfoDto> contacts;
}