package eu.allgeier.tech_radar.technology;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import eu.allgeier.tech_radar.AllgeierTechRadarApplication;
import eu.allgeier.tech_radar.quadrant.Quadrant;
import eu.allgeier.tech_radar.quadrant.QuadrantRepository;
import eu.allgeier.tech_radar.ring.Ring;
import eu.allgeier.tech_radar.ring.RingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(classes = { AllgeierTechRadarApplication.class })
public class TechnologyServiceTest {

    @Autowired
    private TechnologyService technologyService;

    @MockBean
    private TechnologyRepository technologyRepository;
    @MockBean
    private QuadrantRepository quadrantRepository;
    @MockBean
    private RingRepository ringRepository;

    private static Technology validTechnology, persistentTechnology;
    private static Ring validRing;
    private static Quadrant validQuadrant;

    @BeforeAll
    static void init() {
        validRing = new Ring(0, "ValidRing", "0xFFFFFF");
        validQuadrant = new Quadrant(0, "ValidQuadrant");
        validTechnology = new Technology("ValidTechnology", validRing, validQuadrant, 1);
        persistentTechnology = new Technology(validTechnology);
        persistentTechnology.setLabel("PersistentTechnology");
    }

    @BeforeEach
    void api() {
        when(this.ringRepository.findAll())
                .thenReturn(Flux.just(validRing));
        when(this.quadrantRepository.findAll())
                .thenReturn(Flux.just(validQuadrant));
        when(this.technologyRepository.findAll())
                .thenReturn(Flux.just(persistentTechnology));
    }

    @Test
    public void shouldNotCreateTechnology_ifRingUnvalid() {

        Ring unvalidRing = new Ring(0, "UnValidRing", "0xFFFFFF");
        Technology unvalidTechnology = new Technology(validTechnology);
        unvalidTechnology.setRing(unvalidRing);
        unvalidTechnology.setLabel("UnvalidTestTechnology");

        when(this.technologyRepository.save(eq(unvalidTechnology)))
                .thenReturn(Mono.error(new IllegalStateException()));

        StepVerifier.create(technologyService.createTechnology(unvalidTechnology))
                .expectNext(unvalidTechnology)
                .verifyComplete();

    }

    @Test
    public void shouldNotCreateTechnology_ifQuadrantUnvalid() {

        Quadrant unvalidQuadrant = new Quadrant(0, "UnvalidQuadrant");
        Technology unvalidTechnology = new Technology(validTechnology);
        unvalidTechnology.setQuadrant(unvalidQuadrant);
        unvalidTechnology.setLabel("UnvalidTestTechnology");

        when(this.technologyRepository.save(eq(unvalidTechnology)))
                .thenReturn(Mono.error(new IllegalStateException()));

        StepVerifier.create(technologyService.createTechnology(unvalidTechnology))
                .expectNext(unvalidTechnology)
                .verifyComplete();

    }

    @Test
    public void shouldNotCreateTechnology_ifTechnologyLabelExists() {

        when(this.technologyRepository.save(eq(persistentTechnology)))
            .thenReturn(Mono.error(new IllegalStateException()));

        StepVerifier.create(technologyService.createTechnology(persistentTechnology))
                .expectNext(persistentTechnology)
                .verifyComplete();

    }

    @Test
    public void shouldCreateTechnology_ifTechnologyValid() {

        when(this.technologyRepository.save(eq(validTechnology)))
            .thenReturn(Mono.just(validTechnology));

        StepVerifier.create(technologyService.createTechnology(validTechnology))
                .expectNext(validTechnology)
                .verifyComplete();

    }
}
