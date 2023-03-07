package eu.allgeier.tech_radar.controller;

import eu.allgeier.tech_radar.model.Technology;
import eu.allgeier.tech_radar.service.TechnologyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "${allowed.crossorigin.url}")
@RequestMapping("${application.api.version.url}/technologies")
public class TechnologyController {
    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping()
    public ResponseEntity<List<Technology>> getTechnologies() {
        return technologyService.getTechnologies();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable(value = "id") Long id) {
        return technologyService.getTechnologyById(id);
    }

    @GetMapping("/label/{label}")
    public ResponseEntity<Technology> getTechnologyByLabel(@PathVariable(value = "label") String label) {
        return technologyService.getTechnologyByLabel(label);
    }

    @GetMapping("/moved/{moved}")
    public ResponseEntity<List<Technology>> getTechnologiesByMovedStatus(@PathVariable(value = "moved") int moved) {
        return technologyService.getTechnologiesByMovedStatus(moved);
    }

    @GetMapping("/quadrant/{quadrant}")
    public ResponseEntity<List<Technology>> getTechnologiesByQuadrant(@PathVariable(value = "quadrant") int quadrant) {
        return technologyService.getTechnologiesByQuadrant(quadrant);
    }

    @GetMapping("/ring/{ring}")
    public ResponseEntity<List<Technology>> getTechnologiesByRing(@PathVariable(value = "ring") int ring) {
        return technologyService.getTechnologiesByRing(ring);
    }
}