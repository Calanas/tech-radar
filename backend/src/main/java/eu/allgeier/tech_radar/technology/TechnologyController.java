package eu.allgeier.tech_radar.technology;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.allgeier.tech_radar.quadrant.Quadrant;
import eu.allgeier.tech_radar.ring.Ring;
import eu.allgeier.tech_radar.ring.RingService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${application.api.version.url}/technologies")
@CrossOrigin(origins = "http://localhost:4200")
public class TechnologyController {

    @Autowired
    TechnologyService technologyService;
    @Autowired
    RingService ringService;

    @GetMapping()
    public Flux<Technology> getTechnologies(
            @RequestParam(name = "label") Optional<String> label,
            @RequestParam(name = "quadrant") Optional<Quadrant> quadrant,
            @RequestParam(name = "ring") Optional<Ring> ring) {
        return technologyService.getTechnologies(
                label.orElse(null),
                quadrant.orElse(null),
                ring.orElse(null));
    }

    @GetMapping("/{id}")
    public Mono<Technology> getTechnology(@PathVariable("id") String id) {
        return technologyService.getTechnology(id);
    }

    @PutMapping("/{id}")
    public Mono<Technology> updateTechnology(@PathVariable("id") String id, @RequestBody Technology technology) {
        return technologyService.updateTechnology(id, technology);
    }

    @PostMapping()
    public Mono<Technology> createTechnology(@RequestBody Technology technology) {
        return technologyService.createTechnology(technology);
    }

    @DeleteMapping("/{id}")
    public Mono<Technology> deleteTechnology(@PathVariable("id") String id) {
        return technologyService.deleteTechnology(id);
    }

}