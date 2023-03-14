package eu.allgeier.tech_radar.ring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RingServiceImpl implements RingService {

    @Autowired
    RingRepository ringRepository;

    @Override
    public Flux<Ring> getRings() {
        return this.ringRepository.findAll();
    }

    @Override
    public Mono<Ring> createRing(Ring ring) {
        return this.ringRepository.save(ring);
    }

    @Override
    public Mono<Ring> deleteRing(String id) {
        return this.ringRepository
                .findById(id)
                .flatMap(p -> this.ringRepository.deleteById(p.getId())
                        .thenReturn(p));
    }

    @Override
    public Mono<Boolean> existsRing(Ring ring) {
        return this.ringRepository.findByIndexAndNameAndColor(ring.getIndex(), ring.getName(), ring.getColor())
                .flatMap(p -> Mono.just(true))
                .switchIfEmpty(Mono.just(false));
    }
}