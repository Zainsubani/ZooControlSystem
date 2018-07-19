package hibernate.dao;

import hibernate.entity.Animal;
import hibernate.entity.Cage;
import hibernate.entity.Species;
import hibernate.entity.Zookeeper;

public class DBResourceManager {
    private GenericDAOImpl<Animal> animalDAO = new GenericDAOImpl();
    private GenericDAOImpl<Cage> cageDAO = new GenericDAOImpl();
    private GenericDAOImpl<Species> speciesDAO = new GenericDAOImpl();
    private GenericDAOImpl<Zookeeper> zookeeperDAO = new GenericDAOImpl();

    public GenericDAOImpl<Animal> getAnimalDAO() {
        return animalDAO;
    }

    public GenericDAOImpl<Cage> getCageDAO() {
        return cageDAO;
    }

    public GenericDAOImpl<Species> getSpeciesDAO() {
        return speciesDAO;
    }

    public GenericDAOImpl<Zookeeper> getZookeeperDAO() {
        return zookeeperDAO;
    }

    public DBResourceManager(){
        animalDAO = new GenericDAOImpl();
        cageDAO = new GenericDAOImpl();
        speciesDAO = new GenericDAOImpl();
        zookeeperDAO = new GenericDAOImpl();
    }
}
