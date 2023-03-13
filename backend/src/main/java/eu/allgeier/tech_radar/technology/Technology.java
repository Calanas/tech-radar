package eu.allgeier.tech_radar.technology;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import eu.allgeier.tech_radar.quadrant.Quadrant;
import eu.allgeier.tech_radar.ring.Ring;

@Document(collectionName = "Technologies")
public class Technology {

    @DocumentId
    private String id;
    private String label;
    private Ring ring;
    private Quadrant quadrant;
    private Integer moved;

    

    public Technology(String id, String label, Ring ring, Quadrant quadrant, Integer moved) {
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

    public Ring getRing() {
        return ring;
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public Quadrant getQuadrant() {
        return quadrant;
    }

    public void setQuadrant(Quadrant quadrant) {
        this.quadrant = quadrant;
    }

    public Integer getMoved() {
        return moved;
    }

    public void setMoved(Integer moved) {
        this.moved = moved;
    }

    public Technology(String label, Ring ring, Quadrant quadrant, Integer moved) {
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
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Technology [label=" + label + ", ring=" + ring + ", quadrant=" + quadrant + ", moved=" + moved + "]";
    }
}
