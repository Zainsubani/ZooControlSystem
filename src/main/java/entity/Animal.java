package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ANIMAL")
public class Animal {

    @Column(name = "ANIMAL_NAME")
    private String name;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "id")
    private Cage cage;

    @ManyToOne
    @JoinColumn(name = "id")
    private Zookeeper zookeeper;

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
}
