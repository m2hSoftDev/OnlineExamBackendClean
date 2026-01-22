package com.example.onlineExamSystem;

/**
 * Teacher.java
 * OOP CONCEPT: INHERITANCE
 */
public class Teacher extends User {
    private String teacherName;
    private String designation;

    // Default Constructor
    public Teacher() {
        super();
    }

    // Parameterized Constructor
    public Teacher(String uid, String email, String teacherName, String designation) {
        super(uid, email, "teacher");
        this.teacherName = teacherName;
        this.designation = designation;
    }

    // --- ENCAPSULATION: Getters and Setters ---

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    // --- POLYMORPHISM: Implementing the abstract method from User ---
    @Override
    public String getDisplayName() {
        return "Prof. " + this.teacherName + " [" + this.designation + "]";
    }
}