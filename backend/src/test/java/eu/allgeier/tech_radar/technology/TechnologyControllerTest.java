package eu.allgeier.tech_radar.technology;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest(TechnologyController.class)
public class TechnologyControllerTest {
    private final String endpoint = "/api/v1/technologies";
    @MockBean
    private TechnologyService technologyService;

    @Autowired
    private WebTestClient webTestClient;

    private static List<Technology> technologies;

    @BeforeAll
    static void setup() {
        String[] technologiesNames = {"JavaScript", "Java", "C++", "Quarkus"};
        Technology[] technologiesArray = new Technology[technologiesNames.length];
        for (int i = 0; i < technologiesNames.length; ++i) {
            Technology technology = new Technology(technologiesNames[i], i, i, i);
            technology.setId(String.valueOf(i));
            technologiesArray[i] = technology;
        }
        technologies = List.of(technologiesArray);
    }
    @Test
    public void shouldGetAllTechnologiesWhenRequestParameterMissing() {
        when(technologyService.getTechnologies(null, null, null))
                .thenReturn(Flux.fromIterable(technologies));

        webTestClient.get().uri(endpoint)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Technology.class).hasSize(technologies.size());
    }

    @Test
    public void shouldGetTechnologyById() {
        Technology expected = technologies.get(0);
        String technologyId = expected.getId();
        when(technologyService.getTechnology(technologyId)).thenReturn(Mono.just(expected));

        webTestClient.get().uri(endpoint + "/" + technologyId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Technology.class).isEqualTo(expected);
    }

    @Test
    public void shouldGetTechnologiesByLabel() {
        Technology expected = technologies.get(1);
        String technologyLabel = expected.getLabel();
        when(technologyService.getTechnologies(technologyLabel, null, null))
                .thenReturn(Flux.just(expected));

        Technology actual = webTestClient.get().uri(endpoint + "?label=" + technologyLabel)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Technology.class)
                .returnResult().getResponseBody().get(0);

        assertEquals(actual, expected);
    }

    @Test
    public void shouldGetTechnologiesByQuadrant() {
        Technology expected = technologies.get(2);
        Integer technologyQuadrant = expected.getQuadrant();
        when(technologyService.getTechnologies(null, technologyQuadrant , null))
                .thenReturn(Flux.just(expected));

        Technology actual = webTestClient.get().uri(endpoint + "?quadrant=" + technologyQuadrant)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Technology.class)
                .returnResult().getResponseBody().get(0);

        assertEquals(actual, expected);
    }

    @Test
    public void shouldGetTechnologiesByRing() {
        Technology expected = technologies.get(3);
        Integer technologyRing = expected.getRing();
        when(technologyService.getTechnologies(null, null , technologyRing))
                .thenReturn(Flux.just(expected));

        Technology actual = webTestClient.get().uri(endpoint + "?ring=" + technologyRing)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Technology.class)
                .returnResult().getResponseBody().get(0);

        assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnNoContentWithInvalidRequestParams() {
        String invalidRequestParam = "TEST";

        when(technologyService.getTechnologies(any(), any() , any()))
                .thenReturn(Flux.empty());

        webTestClient.get().uri(endpoint + "?" + invalidRequestParam + "=")
                .exchange()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(0);
    }

    @Test
    public void shouldUpdateTechnology() {
        Technology repositoryTechnology = technologies.get(0);
        String techToUpdateID = repositoryTechnology.getId();

        Technology updateTechnology = new Technology(
                repositoryTechnology.getLabel(),
                repositoryTechnology.getRing(),
                repositoryTechnology.getQuadrant(),
                repositoryTechnology.getMoved());

        updateTechnology.setLabel("NEW_LABEL");
        updateTechnology.setId(techToUpdateID);

        when(technologyService.updateTechnology(eq(techToUpdateID), eq(updateTechnology)))
                .thenReturn(Mono.just(updateTechnology));

        webTestClient.put().uri(endpoint + "/" + techToUpdateID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(updateTechnology), Technology.class)
                .exchange()
                .expectBody(Technology.class).isEqualTo(updateTechnology);
    }

    @Test
    public void shouldCreateNewTechnology() {
        Technology newTechnology = new Technology("TEST", 1, 1, 1);
        newTechnology.setId("1");

        when(technologyService.createTechnology(any())).thenReturn(Mono.just(newTechnology));

        webTestClient.post()
                .uri(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(newTechnology), Technology.class)
                .exchange()
                .expectBody(Technology.class).isEqualTo(newTechnology);
    }

    @Test
    public void shouldDeleteTechnology() {
        String technologyToDeleteID = technologies.get(0).getId();
        when(technologyService.deleteTechnology(technologyToDeleteID))
                .thenReturn(Mono.just(technologies.get(0)));

        webTestClient
                .delete()
                .uri(endpoint + "/" + technologyToDeleteID)
                .exchange()
                .expectBody(Technology.class).isEqualTo(technologies.get(0));
    }
}
