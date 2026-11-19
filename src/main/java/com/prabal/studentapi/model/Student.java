package com.prabal.studentapi.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 100)
    private String courseName;

    @Column(nullable = false)
    private double programmingScore;

    @Column(nullable = false)
    private double mathematicsScore;

    @ElementCollection
    @CollectionTable(name = "student_skills", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "skill")
    private List<String> technicalSkills = new ArrayList<>();

    public Student() {}

    public Student(String name, String email, String courseName,
                   double programmingScore, double mathematicsScore, List<String> technicalSkills) {
        this.name = name;
        this.email = email;
        this.courseName = courseName;
        this.programmingScore = programmingScore;
        this.mathematicsScore = mathematicsScore;
        this.technicalSkills = technicalSkills == null ? new ArrayList<>() : new ArrayList<>(technicalSkills);
    }

    public double getAverageScore() {
        return Math.round(((programmingScore + mathematicsScore) / 2.0) * 100.0) / 100.0;
    }

    public String getPerformanceCategory() {
        double average = getAverageScore();
        if (average >= 85) return "Excellent";
        if (average >= 70) return "Good";
        if (average >= 50) return "Average";
        return "Needs Improvement";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public double getProgrammingScore() { return programmingScore; }
    public void setProgrammingScore(double programmingScore) { this.programmingScore = programmingScore; }
    public double getMathematicsScore() { return mathematicsScore; }
    public void setMathematicsScore(double mathematicsScore) { this.mathematicsScore = mathematicsScore; }
    public List<String> getTechnicalSkills() { return technicalSkills; }
    public void setTechnicalSkills(List<String> technicalSkills) {
        this.technicalSkills = technicalSkills == null ? new ArrayList<>() : new ArrayList<>(technicalSkills);
    }
}
