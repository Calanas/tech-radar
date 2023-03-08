package eu.allgeier.tech_radar.technology;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    TechnologyRepository technologyRepository;

    @Override
    public Flux<Technology> getTechnologies() throws InterruptedException, ExecutionException {
        return this.technologyRepository.findAll();
    }

    @Override
    public Mono<Technology> saveTechnology(Technology technology) {
        return technologyRepository.save(technology);
    }

    @Override
    public Mono<Technology> deleteTechnology(String id) {
        return this.technologyRepository
                    .findById(id)
                    .flatMap(p -> this.technologyRepository.deleteById(p.getId())
                    .thenReturn(p));
    }

    @Override
    public Mono<Technology> updateTechnology(Long id, Technology newTechnology) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTechnology'");
    }

    @Override
    public Flux<Technology> getTechnology(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTechnology'");
    }

    @Override
    public Flux<Technology> filterTechnologies(String label, Integer quadrant, Integer ring) {
        return this.technologyRepository.findAll()
                                .filter(p -> label == null ? true : p.getLabel().equals(label))
                                .filter(p -> quadrant == null ? true : p.getQuadrant() == quadrant)
                                .filter(p -> ring == null ? true : p.getRing() == ring);

    }


    // public String saveTechnology(Technology technology) throws
    // InterruptedException, ExecutionException {
    // Firestore dbFirestore = FirestoreClient.getFirestore();
    // ApiFuture<DocumentReference> addedDocRef;

    // // Map<String, Object> docData = new HashMap<String, Object>() {{
    // // put("label", technology.label());
    // // put("ring", technology.ring());
    // // put("quadrant", technology.quadrant());
    // // put("moved", technology.moved());
    // // }};

    // ObjectMapper oMapper = new ObjectMapper();
    // Map<String, Object> docData = oMapper.convertValue(technology, Map.class);
    // if (technology.label() != null) {
    // addedDocRef = dbFirestore.collection(COL_NAME).add(docData);
    // System.out.println("Added document with ID: " + addedDocRef.get().getId());
    // return addedDocRef.get().getId();
    // } else
    // return null;
    // }

    // public String updateTechnology(Technology technology) throws
    // InterruptedException, ExecutionException {
    // Firestore db = FirestoreClient.getFirestore();
    // ApiFuture<QuerySnapshot> future =
    // db.collection(COL_NAME).whereEqualTo("label", technology.label()).get();
    // List<QueryDocumentSnapshot> documents = future.get().getDocuments();

    // ObjectMapper oMapper = new ObjectMapper();
    // Map<String, Object> docData = oMapper.convertValue(technology, Map.class);

    // for (DocumentSnapshot document : documents) {
    // document.getReference().set(docData);
    // System.out.println(document.getId() + " => " + docData);
    // }
    // return "";
    // }

    // public String deleteTechnology(String label) {
    // return null;
    // }

}