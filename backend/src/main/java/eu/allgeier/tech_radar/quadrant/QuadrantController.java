package eu.allgeier.tech_radar.quadrant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${application.api.version.url}/quadrants")
@CrossOrigin(origins = "http://localhost:4200")
public class QuadrantController {
    @Autowired
    QuadrantService quadrantService;

    @GetMapping()
    Flux<Quadrant> getQuadrants() {
        return this.quadrantService.getQuadrants();
    }

    @PostMapping()
    Mono<Quadrant> createQuadrant(@RequestBody Quadrant quadrant) {
        return this.quadrantService.createQuadrant(quadrant);
    }

    @DeleteMapping("/{id}")
    Mono<Quadrant> deleteQuadrant(@PathVariable("id") String id) {
        return this.quadrantService.deleteQuadrant(id);
    }

}
