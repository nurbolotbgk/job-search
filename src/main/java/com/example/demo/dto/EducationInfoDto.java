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

    @NotBlank(message = "Учебное заведение обязательно")
    private String institution;

    @NotBlank(message = "Программа обучения обязательна")
    private String program;

    private LocalDate startDate;

    private LocalDate endDate;

    @NotBlank(message = "Учебная степень обязательна")
    private String degree;

    @AssertTrue(message = "Дата начала учебы не может быть позже даты конца учебы")
    public boolean isDatesValid() {
        if (startDate == null || endDate == null) {
            return true;
        }

        return !startDate.isAfter(endDate);
    }
}
