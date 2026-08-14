package com.example.demo.repository;

import com.example.demo.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findByCategory_Id(Integer categoryId);

    List<Resume> findByUser_Id(Long userId);
}