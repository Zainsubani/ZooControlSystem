package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CAGE")
public class Cage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Set<Animal> animalsInCage = new HashSet<Animal>();

    public void setAnimalsInCage(Set<Animal> animalsInCage) {
        this.animalsInCage = animalsInCage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "cage", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Animal> getAnimalsInCage() {
        return this.animalsInCage;
    }
}
