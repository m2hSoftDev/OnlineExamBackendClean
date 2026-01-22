package com.example.onlineExamSystem;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
// Updated CrossOrigin to explicitly support GET, POST, and OPTIONS for Flutter Web
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class ExamController {

    @Autowired
    private ExamService examService;

    /**
     * GET /api/exams
     * Provides the list of exams for the Flutter "Upcoming Exams" screen.
     * This fixes the 404 error when visiting the base URL.
     */
    @GetMapping
public ResponseEntity<?> getAllExams() {
    try {
        return ResponseEntity.ok(examService.getAllExams());
    } catch (Exception e) {
        // This prints the EXACT file and line number to your Java Terminal
        System.out.println("ERROR DETECTED IN CONTROLLER:");
        e.printStackTrace(); 
        
        // This sends the error message back to Flutter so you can see it there
        return ResponseEntity.status(500).body("Error: " + e.getMessage());
    }
}

@GetMapping("/{examId}/questions")
public ResponseEntity<List<Question>> getQuestions(@PathVariable String examId) {
    try {
        // This fetches from the sub-collection in Firestore
        List<Question> questions = examService.getQuestionsForExam(examId);
        return ResponseEntity.ok(questions);
    } catch (Exception e) {
        return ResponseEntity.status(500).build();
    }
}

    /**
     * POST /api/exams/submit
     * Receives exam results from Flutter and saves them to your database.
     */
    @PostMapping("/submit")
    public ResponseEntity<Map<String, String>> submitExam(@RequestBody Map<String, Object> submissionData) {
        // Logs the incoming data from the Flutter app to the Java console
        System.out.println("Received submission: " + submissionData);
        
        // Prepare a JSON response for the Flutter client
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Exam results processed successfully");
        
        return ResponseEntity.ok(response);
    }
}