package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceInfo {
    private Long id;
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
    private Long resumeId;
}
