package com.example.demo.repository;

import com.example.demo.model.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Integer> {
}
