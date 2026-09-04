package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "responded_applicants")
public class RespondedApplicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @ManyToOne
     @JoinColumn(name = "resume_id")
    private Resume resume;
     @ManyToOne
     @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
    private boolean confirmation;

    @OneToMany(mappedBy = "respondedApplicant")
    private List<Message> messages;

}
