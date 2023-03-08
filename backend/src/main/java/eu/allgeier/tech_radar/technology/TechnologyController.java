package eu.allgeier.tech_radar.technology;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${application.api.version.url}/technologies")
public class TechnologyController {

    @Autowired
    TechnologyService technologyService;

    @GetMapping
    public Flux<Technology> filterTechnologies(
            @RequestParam(required = false) String label,
            @RequestParam(required = false) Integer quadrant,
            @RequestParam(required = false) Integer ring) throws InterruptedException, ExecutionException {
        return technologyService.filterTechnologies(label, quadrant, ring);
    }

    @PostMapping
    public Mono<Technology> saveTechnology(@RequestBody Technology technology)
            throws InterruptedException, ExecutionException {
        return technologyService.saveTechnology(technology);
    }

    @DeleteMapping("deleteTechnology/{id}")
    public Mono<Technology> deleteTechnology(@PathVariable String id) {
        return technologyService.deleteTechnology(id);
    }

    @PutMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Technology> updateTechnology(@PathVariable(name = "id") Long id,
            @RequestBody Technology newTechnology) {
        return technologyService.updateTechnology(id, newTechnology);
    }

    // @PutMapping("/updateTechnology")
    // public String updateTechnology(@RequestBody Technology technology) throws
    // InterruptedException, ExecutionException {
    // return technologyService.updateTechnology(technology);
    // }

    // @DeleteMapping("/deleteTechnology")
    // public String deleteTechnology(@RequestParam String label) {
    // return technologyService.deleteTechnology(label);
    // }
}
