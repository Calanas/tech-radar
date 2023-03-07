package eu.allgeier.tech_radar.controller;

import eu.allgeier.tech_radar.model.Technology;
import eu.allgeier.tech_radar.service.TechnologyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "${allowed.crossorigin.url}")
@RequestMapping("${application.api.version.url}/technologies")
public class TechnologyController {
    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping()
    public List<Technology> getTechnologies
            (@RequestParam(name = "label") Optional<String> label,
             @RequestParam(name = "quadrant") Optional<Integer> quadrant,
             @RequestParam(name = "moved") Optional<Integer> moved) {
        List<Technology> technologies = technologyService.getTechnologies(label, quadrant, moved);
        return technologies;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable(value = "id") Long id) {
        return technologyService.getTechnologyById(id);
    }
}