package eu.allgeier.tech_radar.technology;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import eu.allgeier.tech_radar.AllgeierTechRadarApplication;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AllgeierTechRadarApplication.class)
public class TechnologyDatabase {

	@Autowired
	private TechnologyRepository technologyRepository;

	private Technology testTechnology;

	@Before
	public void init() {
		this.testTechnology = new Technology("TestTechnology", 1, 3, 1);
	}


	@Test
	public void databaseConnection() {
		assert technologyRepository != null;
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
		assert technologyLabelsSet.size() == technologyLabels.size() : "Duplicate Technologies in Database!";
	}

	@Test
	public void allTechnologies_ShouldHaveValidValues() {
		List<Technology> technologyLabels = technologyRepository.findAll().collectList().block();
		for (Technology technology : technologyLabels) {
			assert technology.getQuadrant() >= 1 && technology.getQuadrant() < 5
					: String.format("Technology %s is in quadrant %d!", technology.getLabel(),
							technology.getQuadrant());
			assert technology.getRing() >= 1 && technology.getRing() < 5
					: String.format("Technology %s is in ring %d!", technology.getLabel(),
							technology.getRing());
			assert technology.getMoved() >= -3 && technology.getMoved() <= 3
					: String.format("Technology %s moved by %d!", technology.getLabel(),
							technology.getMoved());

		}
	}

	@Test
	public void writeRead_ShouldHaveAccess() {
		String resultingId = technologyRepository.save(this.testTechnology).block().getId(); // .subscribe(e ->
		
		try {
		StepVerifier.create(technologyRepository
				.findById(resultingId).log())
				.expectNext(this.testTechnology)
				.verifyComplete();
		} finally {
			technologyRepository.deleteById(this.testTechnology.getId()).subscribe();
		}
	}

	@Test
	public void updateTechnology_ShouldUpdateInDatabase() {
		String resultingId = technologyRepository.save(this.testTechnology).block().getId(); // .subscribe(e ->

		try
		{
			StepVerifier.create(technologyRepository
					.findByLabel("TestTechnology").log())
					.expectNextMatches(t -> t.getLabel().equals("TestTechnology"))
					.verifyComplete();

			this.technologyRepository
					.findById(resultingId)
					.flatMap(p -> {
						p.setLabel(this.testTechnology.getLabel());
						p.setMoved(this.testTechnology.getMoved());
						p.setRing(this.testTechnology.getRing());
						p.setQuadrant(this.testTechnology.getQuadrant());
						return this.technologyRepository.save(p);
					});
		}
		finally {
			technologyRepository.deleteById(resultingId).subscribe();
		}
		
	}



}
