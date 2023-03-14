package eu.allgeier.tech_radar.quadrant;

import org.springframework.stereotype.Repository;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

@Repository
public interface QuadrantRepository extends FirestoreReactiveRepository<Quadrant> {
}
