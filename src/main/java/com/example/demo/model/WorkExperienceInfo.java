package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "work_experience_info")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer years;
    @Column(name = "company_name")
    private String companyName;
    private String position;
    private String responsibilities;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
