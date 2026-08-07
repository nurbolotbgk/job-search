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
    @NotNull(message = "User ID should not be null")
    @Positive(message = "User ID should be positive")
    private Long userId;
    @NotBlank(message = "Name should not be empty")
    @Size(min = 3, max = 100, message = "Name length should be 3 to 100")
    private String name;
    @NotNull(message = "Category ID should not be null")
    @Positive(message = "Category ID should be positive")
    private Integer categoryId;
    @NotNull(message = "Salary should not be null")
    @Positive(message = "Salary should not be negative")
    private Double salary;
    @NotNull(message = "Active should not be null")
    private Boolean active;
    @NotNull(message = "Created date should not be null")
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    @Positive(message = "ID should be positive")
    private Long id;

    private List<WorkExperienceInfoDto> workExperiences;
    private List<EducationInfoDto> educations;
    private List<ContactInfoDto> contacts;
}
