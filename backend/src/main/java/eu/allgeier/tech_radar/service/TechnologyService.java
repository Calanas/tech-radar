package eu.allgeier.tech_radar.service;

import eu.allgeier.tech_radar.model.Technology;
import eu.allgeier.tech_radar.repositories.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    @Autowired
    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    private ResponseEntity<List<Technology>> httpResponseOf(List<Technology> technologies) {
        return new ResponseEntity<>(technologies, technologies.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
    private ResponseEntity<Technology> httpResponseOf(Technology technology) {
        return new ResponseEntity<>(technology, technology.id != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    public List<Technology> getTechnologies() {
        return technologyRepository.findAll();
    }

    public List<Technology> getTechnologies(Optional<String> label, Optional<Integer> quadrant, Optional<Integer> moved) {
        List<Technology> allTechnologies = getTechnologies();
        return allTechnologies.stream().filter(technology ->
            technology.label.equals(label.orElse(technology.label))
                    && technology.quadrant == quadrant.orElse(technology.quadrant)
                    && technology.moved == moved.orElse(technology.moved)
        ).toList();
    }

    public ResponseEntity<Technology> getTechnologyById(Long id) {
        return httpResponseOf(technologyRepository.findById(id).orElseGet(Technology::new));
    }

    public ResponseEntity<Technology> getTechnologyByLabel(String label) {
        return httpResponseOf(technologyRepository.findByLabel(label).orElseGet(Technology::new));
    }

    public ResponseEntity<List<Technology>> getTechnologiesByMovedStatus(int movedStatus) {
        return httpResponseOf(technologyRepository.findByMoved(movedStatus));
    }

    public ResponseEntity<List<Technology>> getTechnologiesByQuadrant(int quadrant) {
        return httpResponseOf(technologyRepository.findByQuadrant(quadrant));
    }

    public ResponseEntity<List<Technology>> getTechnologiesByRing(int ring) {
        return httpResponseOf(technologyRepository.findByRing(ring));
    }

    public ResponseEntity<Technology> updateTechnology(Long id, Technology entry) {
        return technologyRepository.findById(id)
                .map(technology -> {
                    technology.label= entry.label;
                    technology.ring = entry.ring;
                    technology.moved = entry.moved;
                    technology.quadrant = entry.quadrant;
                    return new ResponseEntity<>(technologyRepository.save(technology), HttpStatus.OK);
                })
                .orElseGet(() -> {
                    entry.id = id;
                    return new ResponseEntity<>(technologyRepository.save(entry), HttpStatus.CREATED);
                });
    }

    public ResponseEntity<Long> deleteTechnology(Long id) {
        technologyRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}