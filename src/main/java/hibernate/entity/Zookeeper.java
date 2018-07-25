package hibernate.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ZOOKEEPER")
public class Zookeeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToMany(mappedBy = "zookeeper", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Animal> animalSet = new HashSet<Animal>();

    public Set<Animal> getAnimalSet() {
        return animalSet;
    }

    public void setAnimalSet(Set<Animal> animalSet) {
        this.animalSet = animalSet;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return String.format("{ \"firstName\" : \"%s\", \"lastName\" : \"%s\", \"zookeeperId\" : \"%d\"}", firstName,
                lastName, id);
    }

    public Zookeeper(String  firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
