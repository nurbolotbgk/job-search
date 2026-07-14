package com.example.demo.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ResumeDto {
    private int application_id;
    private String name;
    private int category_id;
    private double salary;
    private boolean isActive;
    private Date created_date;
    private Date update_time;
    private int id;
}
