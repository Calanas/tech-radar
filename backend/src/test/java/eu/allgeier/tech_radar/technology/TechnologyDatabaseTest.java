package eu.allgeier.tech_radar.technology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import eu.allgeier.tech_radar.AllgeierTechRadarApplication;
import eu.allgeier.tech_radar.quadrant.Quadrant;
import eu.allgeier.tech_radar.ring.Ring;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(classes = AllgeierTechRadarApplication.class)
public class TechnologyDatabaseTest {

	@Autowired
	private TechnologyRepository technologyRepository;

	private static Technology testTechnology;

	@BeforeAll
	static void init() {
		Ring ring = new Ring(0, "RingTest", "0xFFFFFF");
		Quadrant quadrant = new Quadrant(0, "QuadrantTest");
		testTechnology = new Technology("TestTechnology", ring, quadrant, 1);
	}

	@Test
	public void databaseConnection() {
		assertNotNull(technologyRepository);
	}

	@Test
	public void readFirstTechnology_ShouldShowTechnology() {
		Flux<Technology> allTechnologiesStream = technologyRepository.findAll();
		StepVerifier.create(allTechnologiesStream.log())
				.expectNextMatches(e -> e instanceof Technology)
				.thenCancel()
				.verify();
	}

	@Test
	public void allTechnologies_ShouldHaveUniqueLabels() {
		List<String> technologyLabels = technologyRepository.findAll()
				.map(e -> e.getLabel())
				.collectList().block();
		Set<String> technologyLabelsSet = new HashSet<String>(technologyLabels);
		assertEquals(technologyLabelsSet.size(), technologyLabels.size(), "Duplicate Technologies in Database!");
	}

	@Test
	public void allTechnologies_ShouldHaveValidValues() {
		List<Technology> technologyLabels = technologyRepository.findAll().collectList().block();
		for (Technology technology : technologyLabels) {
			assertTrue(technology.getQuadrant().getIndex() >= 0 && technology.getQuadrant().getIndex() < 4,
					String.format("Technology %s is in quadrant %s!", technology.getLabel(),
							technology.getQuadrant().getName()));
			assertTrue(technology.getRing().getIndex() >= 0 && technology.getRing().getIndex() < 4,
					String.format("Technology %s is in ring %s!", technology.getLabel(),
							technology.getRing().getName()));
			assertTrue(technology.getMoved() >= -3 && technology.getMoved() <= 3,
					String.format("Technology %s moved by %d!", technology.getLabel(),
							technology.getMoved()));

		}
	}

	@Test
	public void writeRead_ShouldHaveAccess() {
		String resultingId = technologyRepository.save(testTechnology).block().getId();
		try {
			StepVerifier.create(technologyRepository
					.findById(resultingId).log())
					.expectNext(testTechnology)
					.verifyComplete();
		} finally {
			technologyRepository.deleteById(testTechnology.getId()).subscribe();
		}
	}

	@Test
	public void updateTechnology_ShouldUpdateInDatabase() {
		String resultingId = technologyRepository.save(testTechnology).block().getId();
		try {
			StepVerifier.create(technologyRepository
					.findByLabel("TestTechnology").log())
					.expectNextMatches(t -> t.getLabel().equals("TestTechnology"))
					.verifyComplete();

			this.technologyRepository
					.findById(resultingId)
					.flatMap(p -> {
						p.setLabel(testTechnology.getLabel());
						p.setMoved(testTechnology.getMoved());
						p.setRing(testTechnology.getRing());
						p.setQuadrant(testTechnology.getQuadrant());
						return this.technologyRepository.save(p);
					});
		} finally {
			technologyRepository.deleteById(resultingId).subscribe();
		}

	}

}
