package eu.allgeier.tech_radar.repositories;

import eu.allgeier.tech_radar.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    Optional<Technology> findByLabel(String label);
    List<Technology> findByMoved(int moved);
    List<Technology> findByQuadrant(int quadrant);

    List<Technology> findByRing(int ring);
}
