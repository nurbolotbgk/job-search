package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "responded_applicants")
public class RespondedApplicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     @ManyToOne
     @JoinColumn(name = "resume_id")
    private Resume resume;
     @ManyToOne
     @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
    private boolean confirmation;

}
