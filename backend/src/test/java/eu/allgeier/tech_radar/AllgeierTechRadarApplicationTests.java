package eu.allgeier.tech_radar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import eu.allgeier.tech_radar.technology.Technology;
import eu.allgeier.tech_radar.technology.TechnologyRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AllgeierTechRadarApplication.class)
@AutoConfigureMockMvc
public class AllgeierTechRadarApplicationTests {

	@Autowired
	private TechnologyRepository technologyRepository;

	@Test
	public void testConnectionToDatabase() throws IOException, InterruptedException, ExecutionException {
		FileInputStream refreshToken = new FileInputStream(
				"C:\\Users\\Hermann Luft\\Documents\\TechRadar\\tech-radar\\backend\\src\\main\\resources\\tech-radar-379911-b814391efe1e.json");

		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance()
				.toBuilder()
				.setProjectId("tech-radar-379911")
				.setCredentials(GoogleCredentials.fromStream(refreshToken))
				.build();
		Firestore db = firestoreOptions.getService();
		ApiFuture<QuerySnapshot> future = db.collection("TechRadarEntry").whereEqualTo("label", "Terraform").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		for (DocumentSnapshot document : documents) {
			assert false;
		}

		// .limit(1)
		// .get()
		// .then((snapshot) {
		// if (checkSnapshot.size == 0) {
		// print("Collection Absent");
		// }
		// });;

		// ApiFuture<WriteResult> future =
		// db.collection("citiTechRadarEntryes").document("LA").set(docData);

	}

	@Test
	public void findTechnologyById_ifPresent() {
		Technology testTechnology = new Technology("TestTechnology", 1, 0, 1);
		technologyRepository.save(testTechnology).subscribe();
		StepVerifier.create(technologyRepository
				.findByLabel(testTechnology.getLabel()).log())
				.expectNext(testTechnology)
				.verifyComplete();
		technologyRepository.deleteById(testTechnology.getId()).subscribe();
	}

}
