package eu.allgeier.tech_radar.technology;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

@Document(collectionName = "TechRadarEntry")
public class Technology {

    @DocumentId
    String id;
    String label;
    Integer ring;
    Integer quadrant;
    Integer moved;

    public Technology() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getRing() {
        return ring;
    }

    public void setRing(Integer ring) {
        this.ring = ring;
    }

    public Integer getQuadrant() {
        return quadrant;
    }

    public void setQuadrant(Integer quadrant) {
        this.quadrant = quadrant;
    }

    public Integer getMoved() {
        return moved;
    }

    public void setMoved(Integer moved) {
        this.moved = moved;
    }

    public Technology(String label, Integer ring, Integer quadrant, Integer moved) {
        this.label = label;
        this.ring = ring;
        this.quadrant = quadrant;
        this.moved = moved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
