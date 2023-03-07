package eu.allgeier.tech_radar.technology;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "api/v1/")
public class TechnologyController {

    // private final FirestoreTemplate firestoreTemplate;
    // private final TechnologyRepository technologyRepository;

    @Autowired
    TechnologyService technologyService;

    // @Autowired
    // public TechnologyController(TechnologyService technologyService,
    //         FirestoreTemplate firestoreTemplate, TechnologyRepository technologyRepository) {
    //     this.firestoreTemplate = firestoreTemplate;
    //     this.technologyService = technologyService;
    //     this.technologyRepository = technologyRepository;
    // }


    @GetMapping("getTechnologies")
    public Flux<Technology> getTechnologies() throws InterruptedException, ExecutionException {
        return technologyService.getTechnologies();
    }

    @PostMapping("saveTechnology")
    public Mono<Technology> saveTechnology(@RequestBody Technology technology) throws InterruptedException, ExecutionException {
        return technologyService.saveTechnology(technology);
    }


    @DeleteMapping("deleteTechnology/{id}")
    public Mono<Technology> deleteTechnology(@PathVariable String id) {
        return technologyService.deleteTechnology(id);
    }

    // @PutMapping("/updateTechnology")
    // public String updateTechnology(@RequestBody Technology technology) throws InterruptedException, ExecutionException {
    //     return technologyService.updateTechnology(technology);
    // }

    // @DeleteMapping("/deleteTechnology")
    // public String deleteTechnology(@RequestParam String label) {
    //     return technologyService.deleteTechnology(label);
    // }
}
