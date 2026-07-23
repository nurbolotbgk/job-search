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

public class ResumeDto {
    private Long userId;
    private String name;
    private Integer categoryId;
    private Double salary;
    private Boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private Long id;
}
