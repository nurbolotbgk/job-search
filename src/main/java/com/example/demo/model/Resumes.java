package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resumes {
    private Integer application_id;
    private String name;
    private Integer category_id;
    private double salary;
    private boolean isActive;
    private Date created_date;
    private Date update_time;
    private Integer id;

}
