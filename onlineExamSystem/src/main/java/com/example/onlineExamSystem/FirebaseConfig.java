// package com.example.onlineExamSystem;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.cloud.firestore.Firestore;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import com.google.firebase.cloud.FirestoreClient;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.ClassPathResource;

// import java.io.IOException;
// import java.io.InputStream;

// @Configuration
// public class FirebaseConfig {

//     @Bean
//     public Firestore firestore() throws IOException {
//         // 1. Check if Firebase is already initialized
//         if (FirebaseApp.getApps().isEmpty()) {
//             // 2. Load the service account file from resources
//             // Using ClassPathResource is safer for Spring Boot apps
//             InputStream serviceAccount = new ClassPathResource("serviceAccountKey.json").getInputStream();

//             FirebaseOptions options = FirebaseOptions.builder()
//                     .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                     .build();

//             FirebaseApp.initializeApp(options);
//             System.out.println(">>> Firebase Initialized Successfully! <<<");
//         }
        
//         // 3. Now return the Firestore instance
//         return FirestoreClient.getFirestore();
//     }
// }

package com.example.onlineExamSystem;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore firestore() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            // 1. Get the JSON string from Railway Environment Variables
            String jsonConfig = System.getenv("FIREBASE_CONFIG");

            if (jsonConfig == null || jsonConfig.isEmpty()) {
                throw new IllegalStateException("ERROR: FIREBASE_CONFIG variable is missing in Railway!");
            }

            // 2. Convert the JSON text into a stream for the Firebase SDK
            InputStream serviceAccount = new ByteArrayInputStream(
                jsonConfig.getBytes(StandardCharsets.UTF_8)
            );

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println(">>> Firebase Initialized via Environment Variable! <<<");
        }
        
        return FirestoreClient.getFirestore();
    }
}