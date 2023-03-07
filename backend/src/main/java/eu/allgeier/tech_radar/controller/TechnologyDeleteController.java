package eu.allgeier.tech_radar.controller;

import eu.allgeier.tech_radar.service.TechnologyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${allowed.crossorigin.url}")
@RequestMapping("${application.api.version.url}/technologies")
public class TechnologyDeleteController {

    private TechnologyService technologyService;

    public TechnologyDeleteController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long>  deleteTechnology(@PathVariable(name = "id") Long id) {
        return technologyService.deleteTechnology(id);
    }
}
