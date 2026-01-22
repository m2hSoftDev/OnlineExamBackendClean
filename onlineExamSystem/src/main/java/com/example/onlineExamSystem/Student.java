package com.example.onlineExamSystem;

/**
 * Student.java
 * OOP CONCEPT: INHERITANCE
 * This class inherits common attributes from User.java
 */
public class Student extends User {
    private String name;
    private String studentId;

    // Default Constructor
    public Student() {
        super();
    }

    // Parameterized Constructor
    public Student(String uid, String email, String name, String studentId) {
        // 'super' calls the constructor of the User parent class
        super(uid, email, "student"); 
        this.name = name;
        this.studentId = studentId;
    }

    // --- ENCAPSULATION: Getters and Setters ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    // --- POLYMORPHISM: Implementing the abstract method from User ---
    @Override
    public String getDisplayName() {
        return "Student: " + this.name + " (ID: " + this.studentId + ")";
    }
}