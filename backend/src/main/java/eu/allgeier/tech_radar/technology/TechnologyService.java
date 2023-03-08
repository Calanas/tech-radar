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

    public Flux<Technology> getTechnologies () throws InterruptedException, ExecutionException;


    public Mono<Technology> saveTechnology(Technology technology);


	public Mono<Technology> deleteTechnology(String id);


    public Mono<Technology> updateTechnology(Long id, Technology newTechnology);


    public Flux<Technology> getTechnology(String id);


    public Flux<Technology> filterTechnologies(String label, Integer quadrant, Integer ring);


    // public String deleteTechnology(String label) {
    //     return null;
    // }
    
}
