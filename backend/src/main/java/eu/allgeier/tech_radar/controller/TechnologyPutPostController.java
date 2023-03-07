package eu.allgeier.tech_radar.controller;

import eu.allgeier.tech_radar.model.Technology;
import eu.allgeier.tech_radar.service.TechnologyService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${allowed.crossorigin.url}")
@RequestMapping("${application.api.version.url}/technologies")
public class TechnologyPutPostController {
    private final TechnologyService technologyService;
    TechnologyPutPostController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PutMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Technology> updateTechnology(@PathVariable(name = "id") Long id, @RequestBody Technology newTechnology) {
        return technologyService.updateTechnology(id, newTechnology);
    }

}
