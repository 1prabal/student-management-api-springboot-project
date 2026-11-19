package com.prabal.studentapi.service;

import com.prabal.studentapi.dto.StudentRequest;
import com.prabal.studentapi.model.Student;
import com.prabal.studentapi.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student addStudent(StudentRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A student with this email already exists");
        }
        Student student = new Student(
                request.getName(), request.getEmail(), request.getCourseName(),
                request.getProgrammingScore(), request.getMathematicsScore(),
                request.getTechnicalSkills()
        );
        return repository.save(student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found: " + id));
    }

    @Transactional
    public Student updateStudent(Long id, StudentRequest request) {
        Student student = getStudentById(id);
        if (!student.getEmail().equalsIgnoreCase(request.getEmail())
                && repository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A student with this email already exists");
        }
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setCourseName(request.getCourseName());
        student.setProgrammingScore(request.getProgrammingScore());
        student.setMathematicsScore(request.getMathematicsScore());
        student.setTechnicalSkills(request.getTechnicalSkills());
        return repository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        repository.delete(student);
    }

    public List<Student> getMeritList() {
        return repository.findAll().stream()
                .sorted(Comparator.comparingDouble(Student::getAverageScore).reversed()
                        .thenComparing(Student::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }
}
