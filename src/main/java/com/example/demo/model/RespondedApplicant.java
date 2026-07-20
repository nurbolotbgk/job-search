package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespondedApplicant {
    private Integer resumeId;
    private Integer vacancyId;
    private boolean confirmation;
    private Integer id;
}
