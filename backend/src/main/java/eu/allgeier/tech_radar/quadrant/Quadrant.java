package eu.allgeier.tech_radar.quadrant;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;


@Document(collectionName = "Quadrants")
public class Quadrant {
    @DocumentId
    private String id;
    private Integer index;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((index == null) ? 0 : index.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Quadrant other = (Quadrant) obj;
        if (index == null) {
            if (other.index != null)
                return false;
        } else if (!index.equals(other.index))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public Quadrant(String id, Integer index, String name) {
        this.id = id;
        this.index = index;
        this.name = name;
    }

    public Quadrant() {
    }

    public Quadrant(Integer index, String name) {
        this.index = index;
        this.name = name;
    }

}
