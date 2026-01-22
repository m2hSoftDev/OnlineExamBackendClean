package com.example.onlineExamSystem;

import java.util.List;

public class Question {
    private String question;
    private List<String> options;
    private int answer; // This is the index of the correct option (0-3)

    // 1. Default Constructor (Required for JSON/Firestore)
    public Question() {}

    // 2. Full Constructor
    public Question(String question, List<String> options, int answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    // --- Getters and Setters ---

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getAnswer() {
        return answer;
    }

    // This is the method that was missing!
    public void setAnswer(int answer) {
        this.answer = answer;
    }
}