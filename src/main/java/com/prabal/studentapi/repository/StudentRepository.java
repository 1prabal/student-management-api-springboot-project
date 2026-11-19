package com.prabal.studentapi.repository;

import com.prabal.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
}
