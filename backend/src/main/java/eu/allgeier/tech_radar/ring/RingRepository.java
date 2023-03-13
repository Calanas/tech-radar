package eu.allgeier.tech_radar.ring;

import org.springframework.stereotype.Repository;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import reactor.core.publisher.Mono;

@Repository
public interface RingRepository extends FirestoreReactiveRepository<Ring> {

    Mono<Boolean> existsByName(String name);

    Mono<Boolean> existsByIndex(Integer name);

    Mono<Ring> findByIndexAndNameAndColor(Integer index, String name, String color);

}
