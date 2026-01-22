package com.example.onlineExamSystem;
import java.util.Date;

/**
 * User.java
 * * CORE OOP CONCEPTS:
 * 1. ABSTRACTION: Declared as 'abstract' because a generic "User" 
 * cannot exist without being a Student or Teacher.
 * 2. ENCAPSULATION: Fields are 'private' and accessed via public 
 * Getters and Setters.
 */
public abstract class User {
    // Private fields (Encapsulation)
    private String uid;
    private String email;
    private String role;
    private Date createdAt;

    // Default Constructor (Required for some mapping tools)
    public User() {}

    // Parameterized Constructor
    public User(String uid, String email, String role) {
        this.uid = uid;
        this.email = email;
        this.role = role;
        this.createdAt = new Date(); // Sets the current time
    }

    // --- GETTERS AND SETTERS (Encapsulation) ---

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Polymorphism Example: 
     * This method can be overridden by Student or Teacher 
     * to provide a specific display name.
     */
    public abstract String getDisplayName();
}