package eu.allgeier.tech_radar.model;

import jakarta.persistence.*;

@Entity
@Table
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String label;
    public int quadrant;
    public int ring;
    public int moved;
}
