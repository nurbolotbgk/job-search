package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByName(String name);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByIdAndRole_Id(Long id, Integer roleId);

    @Query("""
            SELECT DISTINCT u
            FROM User u
            JOIN u.resumes r
            JOIN r.respondedApplicants ra
            WHERE u.role.id = 1
              AND ra.vacancy.id = :vacancyId
            """)
    List<User> findApplicantsForVacancy(@Param("vacancyId") Long vacancyId);

    Page<User> findByRole_Id(Integer roleId, Pageable pageable);

    Optional<User> findByResetPasswordToken(String token);

}
