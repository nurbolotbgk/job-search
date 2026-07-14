package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VacancyDto {
    private String name;
    private String description;
    private Integer category_id;
    private double salary;
    private Integer exp_from;
    private Integer exp_to;
    private boolean is_active;
    private Integer author_id;
    private Date createdDate;
    private Date update_time;
    private Integer id;
}
