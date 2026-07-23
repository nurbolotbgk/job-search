package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    private String name;
    private String description;
    private Integer categoryId;
    private double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean active;
    private Long userId;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private Long id;
}
