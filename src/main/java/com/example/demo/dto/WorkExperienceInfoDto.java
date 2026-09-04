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
    @NotNull(message = "{validation.experience.years.notnull}")
    @Min(value = 0, message = "{validation.experience.years.min}")
    private Integer years;
    @NotBlank(message = "{validation.experience.company.notblank}")
    private String companyName;
    @NotBlank(message = "{validation.experience.position.notblank}")
    private String position;
    private String responsibilities;
}