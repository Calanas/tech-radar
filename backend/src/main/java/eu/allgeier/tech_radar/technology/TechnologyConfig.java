package eu.allgeier.tech_radar.technology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.data.firestore.FirestoreTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TechnologyConfig {
    @Bean
    public FirestoreTemplate firestoreTemplate() {
        return new FirestoreTemplate(null, null, null, null);
    }

    @Bean
    public Firestore getFireStore(@Value("${firebase.credential.path}") String credentialPath) throws IOException {
        var serviceAccount = new FileInputStream(credentialPath);
        var credentials = GoogleCredentials.fromStream(serviceAccount);

        var options = FirestoreOptions.newBuilder()
                .setCredentials(credentials).build();

        return options.getService();
    }
}
