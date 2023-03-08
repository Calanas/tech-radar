package eu.allgeier.tech_radar.controller;

import eu.allgeier.tech_radar.model.Technology;
import eu.allgeier.tech_radar.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "${allowed.crossorigin.url}")
@RequestMapping("${application.api.version.url}/technologies")
public class TechnologyController {
    @Autowired
    private TechnologyService technologyService;


    @GetMapping()
    public List<Technology> getTechnologies
            (@RequestParam(name = "label") Optional<String> label,
             @RequestParam(name = "quadrant") Optional<Integer> quadrant,
             @RequestParam(name = "moved") Optional<Integer> moved) {
        List<Technology> technologies = technologyService.getTechnologies(label, quadrant, moved);
        return technologies;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable(value = "id") Long id) {
        return technologyService.getTechnologyById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Technology> updateTechnology(@PathVariable(name = "id") Long id,
                                                       @RequestBody Technology newTechnology) {
        return technologyService.updateTechnology(id, newTechnology);
    }

    @PostMapping()
    public ResponseEntity<Technology> createTechnology(@RequestBody Technology newTechnology) {
        return technologyService.createTechnology(newTechnology);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long>  deleteTechnology(@PathVariable(name = "id") Long id) {
        return technologyService.deleteTechnology(id);
    }
}