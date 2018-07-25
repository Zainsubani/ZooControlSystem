package hibernate.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SPECIES")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SPECIES_NAME")
    private String name;

    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Animal> animals = new HashSet<Animal>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Animal> getAllAnimals() {
        return this.animals;
    }

    @Override
    public String toString(){
        return String.format("{ \"name\" : \"%s\", \"speciesId\" : \"%d\" }", name, id);
    }

    public Species(String name){
        this.name = name;
    }
}
