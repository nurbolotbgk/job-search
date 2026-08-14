package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "resumes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Double salary;
    private Boolean active;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    @OneToMany(mappedBy = "resume")
    private List<EducationInfo> educationInfos;
    @OneToMany(mappedBy = "resume")
    private List<ContactInfo> contactInfos;
    @OneToMany(mappedBy = "resume")
    private List<WorkExperienceInfo> workExperienceInfos;
    @OneToMany(mappedBy = "resume")
    private List<RespondedApplicant> respondedApplicants;

}
