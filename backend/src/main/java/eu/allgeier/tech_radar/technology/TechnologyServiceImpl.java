package eu.allgeier.tech_radar.technology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.allgeier.tech_radar.quadrant.QuadrantRepository;
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

    public TechnologyServiceImpl(TechnologyRepository technologyRepository, RingRepository ringRepository,
            QuadrantRepository quadrantRepository) {
        this.technologyRepository = technologyRepository;
        this.ringRepository = ringRepository;
        this.quadrantRepository = quadrantRepository;
    }

    @Override
    public Mono<Technology> createTechnology(Technology technology) {
        return Mono.zip(
                this.technologyRepository.findAll().collectList(),
                this.ringRepository.findAll().collectList(),
                this.quadrantRepository.findAll().collectList())
                .filter((zip) -> !zip.getT1().contains(technology) && zip.getT2().contains(technology.getRing())
                        && zip.getT3().contains(technology.getQuadrant()))
                .flatMap(p -> this.technologyRepository.save(technology))
                .then(Mono.just(technology));
    }

    @Override
    public Mono<Void> deleteTechnology(String id) {
        return this.technologyRepository.deleteById(id);
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
                })
                .then(Mono.just(newTechnology));
    }

    @Override
    public Mono<Technology> getTechnology(String id) {
        return this.technologyRepository.findById(id);
    }

    @Override
    public Flux<Technology> getTechnologies(String label, Integer quadrantIndex, Integer ringIndex) {
        return this.technologyRepository.findAll()
                .filter(p -> label == null || p.getLabel().equals(label))
                .filter(p -> quadrantIndex == null || p.getQuadrant().getIndex().equals(quadrantIndex))
                .filter(p -> ringIndex == null || p.getRing().getIndex().equals(ringIndex));
    }

}