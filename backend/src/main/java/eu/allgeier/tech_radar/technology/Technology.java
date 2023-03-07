package eu.allgeier.tech_radar.technology;

import com.google.cloud.firestore.annotation.DocumentId;

@Document(collectionName="TechRadarEntry")
public record Technology (  @DocumentId String label, 
                            int ring, 
                            int quadrant, 
                            int moved   ){
    
}