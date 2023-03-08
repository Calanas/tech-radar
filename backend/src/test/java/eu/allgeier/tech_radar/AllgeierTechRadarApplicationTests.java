package eu.allgeier.tech_radar;

import static org.junit.Assert.assertThat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import eu.allgeier.tech_radar.technology.Technology;
import eu.allgeier.tech_radar.technology.TechnologyRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AllgeierTechRadarApplication.class)
@AutoConfigureMockMvc
public class AllgeierTechRadarApplicationTests {

	@Autowired
	private TechnologyRepository technologyRepository;

	@Test
	public void testConnectionDatabase() {
		assert technologyRepository != null;
	}

	@Test
	public void testReadFirstTechnologyDatabase() {
		Flux<Technology> allTechnologiesStream = technologyRepository.findAll();
		StepVerifier.create(allTechnologiesStream.log())
				.expectNextMatches(e -> e instanceof Technology)
				.thenCancel()
				.verify();
	}

	@Test
	public void testTechnologyUniqueLabels() {
		List<String> technologyLabels = technologyRepository.findAll()
				.map(e -> e.getLabel())
				.collectList().block();
		Set<String> technologyLabelsSet = new HashSet<String>(technologyLabels);
		assert technologyLabelsSet.size() == technologyLabels.size() : "Duplicate Technologies in Database!";
	}

	@Test
	public void testValidValuesDatabase() {
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
	public void testInputOutputDatabase() {
		Technology testTechnology = new Technology("TestTechnology", 1, 3, 1);
		String resultingId = technologyRepository.save(testTechnology).block().getId(); // .subscribe(e ->
																						// sb.append(e.getId()));
		StepVerifier.create(technologyRepository
				.findById(resultingId).log())
				.expectNext(testTechnology)
				.verifyComplete();
		technologyRepository.deleteById(testTechnology.getId()).subscribe();
	}

	@Test
	public void updateTechnologyDatabase () {
		Technology testTechnology = new Technology("TestTechnology", 1, 2, 1);
		String resultingId = technologyRepository.save(testTechnology).block().getId(); // .subscribe(e ->
		
		StepVerifier.create(technologyRepository
				.findByLabel("TestTechnology").log())
				.expectNextMatches(t -> 
					t.getLabel().equals("TestTechnology"))
				.verifyComplete();

		technologyRepository.
	}

}
