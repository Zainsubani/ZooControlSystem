package hibernate.dao;

import hibernate.entity.*;

public class DBResourceManager {
    private static GenericDAOImpl<Animal> animalDAO = new GenericDAOImpl();
    private static GenericDAOImpl<Cage> cageDAO = new GenericDAOImpl();
    private static GenericDAOImpl<Species> speciesDAO = new GenericDAOImpl();
    private static GenericDAOImpl<Zookeeper> zookeeperDAO = new GenericDAOImpl();
    private static GenericDAOImpl<ZooControlSystemUser> usersDAO = new GenericDAOImpl();

    public static GenericDAOImpl<Animal> getAnimalDAO() {
        return animalDAO;
    }

    public static GenericDAOImpl<Cage> getCageDAO() {
        return cageDAO;
    }

    public static GenericDAOImpl<Species> getSpeciesDAO() {
        return speciesDAO;
    }

    public static GenericDAOImpl<Zookeeper> getZookeeperDAO() {
        return zookeeperDAO;
    }

    public static GenericDAOImpl<ZooControlSystemUser> getUsersDAO() {
        return usersDAO;
    }
}
