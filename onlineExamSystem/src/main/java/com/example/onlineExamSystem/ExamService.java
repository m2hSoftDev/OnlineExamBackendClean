package com.example.onlineExamSystem;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ExamService {

    private static final String COLLECTION_NAME = "exams";
    private final Firestore firestore;
    private final SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Autowired
    public ExamService(Firestore firestore) {
        this.firestore = firestore;
    }

    public List<Exam> getAllExams() {
        try {
            QuerySnapshot querySnapshot = firestore.collection(COLLECTION_NAME).get().get();
            return querySnapshot.getDocuments().stream()
                    .map(this::mapDocumentToExam)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error fetching all exams: " + e.getMessage());
            return Collections.emptyList();
        }
    }

   private Exam mapDocumentToExam(QueryDocumentSnapshot doc) {
    try {
        Exam exam = new Exam();
        exam.setId(doc.getId());
        exam.setTitle(doc.getString("title")); // If 'title' isn't in your snippet, make sure it exists
        exam.setDescription(doc.getString("description"));
        
        // FIX 1: durationMinutes is a Number in your DB
        Object duration = doc.get("durationMinutes");
        exam.setDurationMinutes(duration != null ? duration.toString() : "0");

        // FIX 2: Handle dates which are stored as Strings in your DB
        exam.setExpiryDate(parseDate(doc.get("expiryDate")));
        exam.setStartDate(parseDate(doc.get("startDate"))); 
        
        // Optional: Capture creatorEmail or status if needed
        // exam.setStatus(doc.getString("status"));

        return exam;
    } catch (Exception e) {
        System.err.println("Mapping error for doc " + doc.getId() + ": " + e.getMessage());
        return null;
    }
}

private Date parseDate(Object rawDate) {
    if (rawDate == null) return null;
    try {
        // Your DB uses "2026-01-20T23:42:00.000"
        // We need to adjust the format to handle the milliseconds (.000)
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        
        if (rawDate instanceof String) {
            return formatter.parse((String) rawDate);
        } else if (rawDate instanceof com.google.cloud.Timestamp) {
            return ((com.google.cloud.Timestamp) rawDate).toDate();
        }
    } catch (Exception e) {
        System.err.println("Date parsing failed: " + e.getMessage());
    }
    return null;
}

    public List<Question> getQuestionsForExam(String examId) throws ExecutionException, InterruptedException {
        DocumentSnapshot doc = firestore.collection(COLLECTION_NAME).document(examId).get().get();
        if (!doc.exists()) return Collections.emptyList();

        List<Map<String, Object>> questionsRaw = (List<Map<String, Object>>) doc.get("questions");
        if (questionsRaw == null) return Collections.emptyList();

        return questionsRaw.stream().map(data -> {
            Question q = new Question();
            q.setQuestion((String) data.get("question"));
            q.setOptions((List<String>) data.get("options"));
            
            Object answerObj = data.get("answer");
            if (answerObj instanceof Number) {
                q.setAnswer(((Number) answerObj).intValue());
            }
            return q;
        }).collect(Collectors.toList());
    }
}