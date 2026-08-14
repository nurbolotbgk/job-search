package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contacts_info")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "contact_value")
    private String contactValue;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private ContactType contactType;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

}
