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

    

    public Technology(String id, String label, Integer ring, Integer quadrant, Integer moved) {
        this.id = id;
        this.label = label;
        this.ring = ring;
        this.quadrant = quadrant;
        this.moved = moved;
    }

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((ring == null) ? 0 : ring.hashCode());
        result = prime * result + ((quadrant == null) ? 0 : quadrant.hashCode());
        result = prime * result + ((moved == null) ? 0 : moved.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Technology other = (Technology) obj;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        if (ring == null) {
            if (other.ring != null)
                return false;
        } else if (!ring.equals(other.ring))
            return false;
        if (quadrant == null) {
            if (other.quadrant != null)
                return false;
        } else if (!quadrant.equals(other.quadrant))
            return false;
        if (moved == null) {
            if (other.moved != null)
                return false;
        } else if (!moved.equals(other.moved))
            return false;
        return true;
    }

    

}
