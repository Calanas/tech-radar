package eu.allgeier.tech_radar.ring;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

@Document(collectionName = "Rings")
public class Ring {
    @DocumentId
    private String id;
    private Integer index;
    private String name;
    private String color;

    public Ring() {
    }

    public Ring(Integer index, String name, String color) {
        this.index = index;
        this.name = name;
        this.color = color;
    }

    public Ring(String id, Integer index, String name, String color) {
        this.id = id;
        this.index = index;
        this.name = name;
        this.color = color;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((index == null) ? 0 : index.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
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
        Ring other = (Ring) obj;
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
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        return true;
    }

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
