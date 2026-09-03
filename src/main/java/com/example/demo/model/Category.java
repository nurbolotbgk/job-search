package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Vacancy> vacancies;
    @OneToMany(mappedBy = "category")
    private List<Resume> resumes;
}
