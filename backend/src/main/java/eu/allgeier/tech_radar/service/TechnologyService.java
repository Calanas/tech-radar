package eu.allgeier.tech_radar.service;

import eu.allgeier.tech_radar.model.Technology;
import eu.allgeier.tech_radar.repositories.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    @Autowired
    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public List<Technology> getTechnologies() {
        return technologyRepository.findAll();
    }

    public Technology getTechnologyById(Long id) {
        return technologyRepository.findById(id).orElse(new Technology());
    }

    public Technology getTechnologyByLabel(String label) {
        return technologyRepository.findByLabel(label).orElse(new Technology());
    }

    public List<Technology> getTechnologiesByMovedStatus(int movedStatus) {
        return technologyRepository.findByMoved(movedStatus);
    }

    public List<Technology> getTechnologiesByQuadrant(int quadrant) {
        return technologyRepository.findByQuadrant(quadrant);
    }

    public List<Technology> getTechnologiesByRing(int ring) {
        return technologyRepository.findByRing(ring);
    }
}