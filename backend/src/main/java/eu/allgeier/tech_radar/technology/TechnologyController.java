package eu.allgeier.tech_radar.technology;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/technology")
public class TechnologyController {

    private final TechnologyService technologyService;

    
    @Autowired
    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping("/getTechnologies")
    public Technology getTechnologies () throws InterruptedException, ExecutionException {
        return technologyService.getTechnologies();
    }

    @PostMapping("/createTechnology")
    public String createTechnology(@RequestBody Technology technology ) throws InterruptedException, ExecutionException {
        return technologyService.saveTechnology(technology);
    }

    @PutMapping("/updateTechnology")
    public String updateTechnology(@RequestBody Technology technology) throws InterruptedException, ExecutionException {
        return technologyService.updateTechnology(technology);
    }

    @DeleteMapping("/deleteTechnology")
    public String deleteTechnology(@RequestParam String label) {
        return technologyService.deleteTechnology(label);
    }
}
