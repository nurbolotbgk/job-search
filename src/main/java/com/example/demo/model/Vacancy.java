package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {
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
