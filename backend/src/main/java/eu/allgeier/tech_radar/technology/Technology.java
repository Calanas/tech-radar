package eu.allgeier.tech_radar.technology;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import java.util.Objects;

@Document(collectionName = "TechRadarEntry")
public class Technology {

    @DocumentId
    private String id;
    private String label;
    private Integer ring;
    private Integer quadrant;
    private Integer moved;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Technology that = (Technology) o;
        return getLabel().equals(that.getLabel()) && getRing().equals(that.getRing()) && getQuadrant().equals(that.getQuadrant()) && getMoved().equals(that.getMoved());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabel(), getRing(), getQuadrant(), getMoved());
    }
}
