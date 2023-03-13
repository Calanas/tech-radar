package eu.allgeier.tech_radar.ring;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RingService {

    Flux<Ring> getRings();

    Mono<Ring> createRing(Ring ring);

    Mono<Ring> deleteRing(String ring);

    Mono<Boolean> existsRing(Ring ring);

}
