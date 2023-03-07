package eu.allgeier.tech_radar.technology;

import org.springframework.stereotype.Repository;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;


@Repository
public interface TechnologyRepository extends FirestoreReactiveRepository<Technology> {
}
