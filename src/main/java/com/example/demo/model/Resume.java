package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    private Long userId;
    private String name;
    private Integer categoryId;
    private Double salary;
    private Boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private Long id;
}
