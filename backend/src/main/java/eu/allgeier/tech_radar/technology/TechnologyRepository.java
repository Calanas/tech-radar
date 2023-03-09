package eu.allgeier.tech_radar.technology;

import org.springframework.stereotype.Repository;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import reactor.core.publisher.Flux;


@Repository
public interface TechnologyRepository extends FirestoreReactiveRepository<Technology> {
    Flux<Technology> findByLabel(String label);

    Flux<Technology> deleteByLabel(String label);
}
