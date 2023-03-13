package eu.allgeier.tech_radar.technology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autovalue.shaded.kotlin.sequences.TakeWhileSequence;
import eu.allgeier.tech_radar.quadrant.Quadrant;
import eu.allgeier.tech_radar.quadrant.QuadrantRepository;
import eu.allgeier.tech_radar.ring.Ring;
import eu.allgeier.tech_radar.ring.RingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    TechnologyRepository technologyRepository;
    @Autowired
    RingRepository ringRepository;
    @Autowired
    QuadrantRepository quadrantRepository;

    @Override
    public Mono<Technology> createTechnology(Technology technology) {
        return Mono.zip(
                this.technologyRepository.findAll().collectList(),
                this.ringRepository.findAll().collectList(),
                this.quadrantRepository.findAll().collectList())
                .filter((zip) -> !zip.getT1().contains(technology) && zip.getT2().contains(technology.getRing())
                        && zip.getT3().contains(technology.getQuadrant()))
                .flatMap(p -> this.technologyRepository.save(technology).thenReturn(technology));
    }

    @Override
    public Mono<Technology> deleteTechnology(String id) {
        return this.technologyRepository
                .findById(id)
                .flatMap(p -> this.technologyRepository.deleteById(p.getId())
                        .thenReturn(p));
    }

    @Override
    public Mono<Technology> updateTechnology(String id, Technology newTechnology) {
        return this.technologyRepository
                .findById(id)
                .flatMap(p -> {
                    p.setLabel(newTechnology.getLabel());
                    p.setMoved(newTechnology.getMoved());
                    p.setRing(newTechnology.getRing());
                    p.setQuadrant(newTechnology.getQuadrant());
                    return this.technologyRepository.save(p);
                });
    }

    @Override
    public Mono<Technology> getTechnology(String id) {
        return this.technologyRepository.findById(id);
    }

    @Override
    public Flux<Technology> getTechnologies(String label, Quadrant quadrant, Ring ring) {
        return this.technologyRepository.findAll()
                .filter(p -> label == null || p.getLabel().equals(label))
                .filter(p -> quadrant == null || p.getQuadrant().equals(quadrant))
                .filter(p -> ring == null || p.getRing().equals(ring));
    }

}