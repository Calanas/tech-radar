package eu.allgeier.tech_radar.technology;

import org.springframework.stereotype.Repository;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TechnologyRepository extends FirestoreReactiveRepository<Technology> {
    Flux<Technology> findByLabel(String label);

    Flux<Technology> deleteByLabel(String label);

    Mono<Boolean> existsByLabel(String label);

    Mono<Boolean> existsNotByLabel(String label);
}
