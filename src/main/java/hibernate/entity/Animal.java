package hibernate.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "ANIMAL")
public class Animal {

    @Id
    @Column(name = "ANIMAL_NAME")
    private String name;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "CAGE")
    private Cage cage;

    @ManyToOne
    @JoinColumn(name = "ZOOKEEPER")
    private Zookeeper zookeeper;

    @ManyToOne
    @JoinColumn(name = "SPECIES")
    private Species species;

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Zookeeper getZookeeper() {
        return zookeeper;
    }

    public Cage getCage() {
        return cage;
    }

    public Species getSpecies() {
        return species;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
    }

    public void setZookeeper(Zookeeper zookeeper) {
        this.zookeeper = zookeeper;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Animal(String name, Species species, Date dateOfBirth, Cage cage, Zookeeper zookeeper){
        this.name = name;
        this.species = species;
        this.dateOfBirth = dateOfBirth;
        this.cage = cage;
        this.zookeeper = zookeeper;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
        return String.format("{ \"name\" : \"%s\", \"dateOfBirth\": \"" + sdf.format(dateOfBirth) + "\", \"species\" : %s," +
                "\"zookeeper\": %s, \"cage\" : %s }", name, species, zookeeper, cage);
    }
}
