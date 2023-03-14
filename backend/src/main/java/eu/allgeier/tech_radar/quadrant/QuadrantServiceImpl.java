package eu.allgeier.tech_radar.quadrant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QuadrantServiceImpl implements QuadrantService {

    @Autowired
    QuadrantRepository quadrantRepository;

    @Override
    public Flux<Quadrant> getQuadrants() {
        return this.quadrantRepository.findAll();
    }

    @Override
    public Mono<Quadrant> createQuadrant(Quadrant quadrant) {
        return this.quadrantRepository.save(quadrant);
    }

    @Override
    public Mono<Quadrant> deleteQuadrant(String id) {
        return this.quadrantRepository
                .findById(id)
                .flatMap(p -> this.quadrantRepository.deleteById(p.getId())
                        .thenReturn(p));
    }

}
