package eu.allgeier.tech_radar.quadrant;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuadrantService {
    Flux<Quadrant> getQuadrants();

    Mono<Quadrant> createQuadrant(Quadrant quadrant);

    Mono<Quadrant> deleteQuadrant(String id);
}
