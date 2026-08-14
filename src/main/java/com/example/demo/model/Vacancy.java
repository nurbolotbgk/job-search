package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vacancies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private double salary;
    @Column(name = "exp_from")
    private Integer expFrom;
    @Column(name = "exp_to")
    private Integer expTo;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    @OneToMany(mappedBy = "vacancy")
    private List<RespondedApplicant> respondedApplicants;

}
