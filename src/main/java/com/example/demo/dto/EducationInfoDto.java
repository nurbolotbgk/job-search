package com.example.demo.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationInfoDto {

    private Long id;
    @NotBlank(message = "{validation.education.institution.notblank}")
    private String institution;
    @NotBlank(message = "{validation.education.program.notblank}")
    private String program;
    private LocalDate startDate;
    private LocalDate endDate;
    @NotBlank(message = "{validation.education.degree.notblank}")
    private String degree;
    @AssertTrue(message = "{validation.education.dates.valid}")
    public boolean isDatesValid() {
        if (startDate == null || endDate == null) {
            return true;
        }
        return !startDate.isAfter(endDate);
    }
}