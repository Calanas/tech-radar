package eu.allgeier.tech_radar.technology;

import eu.allgeier.tech_radar.quadrant.Quadrant;
import eu.allgeier.tech_radar.ring.Ring;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TechnologyService {

    Mono<Technology> createTechnology(Technology technology);

    Mono<Technology> deleteTechnology(String id);

    Mono<Technology> updateTechnology(String id, Technology newTechnology);

    Mono<Technology> getTechnology(String id);

    Flux<Technology> getTechnologies(String label, Quadrant quadrant, Ring ring);

}