package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "contact_types")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactType {
    @Id
    private Integer id;
    private String type;
    @OneToMany(mappedBy = "contactType")
    private List<ContactInfo> contactInfoList;
}
