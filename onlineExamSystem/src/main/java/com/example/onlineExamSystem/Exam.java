package com.example.onlineExamSystem;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class Exam {
    private String id;
    private String title;
    private String description;
    private String durationMinutes;

    // Use this specific pattern to match Flutter's .toIso8601String()
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date expiryDate;

    public Exam() {}


    private Date startDate; // Add this

    // Standard Getters and Setters...
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    // Getters and Setters
    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(String durationMinutes) { this.durationMinutes = durationMinutes; }
}