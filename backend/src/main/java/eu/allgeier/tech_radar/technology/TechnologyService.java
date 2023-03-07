package eu.allgeier.tech_radar.technology;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firestore.v1.Document;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


public interface TechnologyService {

    public static final String COL_NAME = "TechRadarEntry";
    

    // public List<Technology> getTechnology() {
    //     return List.of(
    //         new Technology(1L, "Ansible", 2, 1, 0)
    //     );
    // }


    // @PostConstruct
    // public void initialize() {
    //     try {
    //         FileInputStream serviceAccount =
    //                 new FileInputStream("./serviceaccount.json");

    //         FirebaseOptions options = FirebaseOptions.builder()
    //                 .setCredentials(GoogleCredentials.fromStream(serviceAccount))
    //                 .setDatabaseUrl("https://firestore.googleapis.com")
    //                 .build();

    //         FirebaseApp.initializeApp(options);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    // }


    public Flux<Technology> getTechnologies () throws InterruptedException, ExecutionException;


    public Mono<Technology> saveTechnology(Technology technology);

    // public String saveTechnology(Technology technology) throws InterruptedException, ExecutionException {
    //     Firestore dbFirestore = FirestoreClient.getFirestore();
    //     ApiFuture<DocumentReference> addedDocRef;

    //     // Map<String, Object> docData  = new HashMap<String, Object>() {{
    //     //     put("label", technology.label());
    //     //     put("ring", technology.ring());
    //     //     put("quadrant", technology.quadrant());
    //     //     put("moved", technology.moved());
    //     // }};
        
    //     ObjectMapper oMapper = new ObjectMapper();
    //     Map<String, Object> docData = oMapper.convertValue(technology, Map.class);
    //     if (technology.label() != null) {
    //         addedDocRef = dbFirestore.collection(COL_NAME).add(docData);
    //         System.out.println("Added document with ID: " + addedDocRef.get().getId());
    //         return addedDocRef.get().getId();
    //     } else
    //         return null;
    // }

    // public String updateTechnology(Technology technology) throws InterruptedException, ExecutionException {
    //     Firestore db = FirestoreClient.getFirestore();
    //     ApiFuture<QuerySnapshot> future = db.collection(COL_NAME).whereEqualTo("label", technology.label()).get();
    //     List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        
    //     ObjectMapper oMapper = new ObjectMapper();
    //     Map<String, Object> docData = oMapper.convertValue(technology, Map.class);

    //     for (DocumentSnapshot document : documents) {
    //         document.getReference().set(docData);
    //         System.out.println(document.getId() + " => " + docData);
    //     }
    //     return "";
    // }

    // public String deleteTechnology(String label) {
    //     return null;
    // }
    
}
