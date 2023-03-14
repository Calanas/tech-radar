package eu.allgeier.tech_radar.ring;

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
@RequestMapping("${application.api.version.url}/rings")
@CrossOrigin(origins = "http://localhost:4200")
public class RingController {

    @Autowired
    RingService ringService;

    @GetMapping()
    public Flux<Ring> getRings() {
        return ringService.getRings();
    }

    @PostMapping()
    public Mono<Ring> createRing(@RequestBody Ring ring) {
        return ringService.createRing(ring);
    }

    @DeleteMapping("/{id}")
    Mono<Ring> deleteRing(@PathVariable("id") String id) {
        return this.ringService.deleteRing(id);
    }
}
