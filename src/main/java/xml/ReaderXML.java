package xml;

import hibernate.dao.DBResourceManager;
import hibernate.dao.GenericDAOImpl;
import hibernate.entity.*;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReaderXML {
    private GenericDAOImpl<Animal> animalDAO;
    private GenericDAOImpl<Cage> cageDAO;
    private GenericDAOImpl<Species> speciesDAO;
    private GenericDAOImpl<Zookeeper> zookeeperDAO;
    private Map<Integer, Serializable> zookeepersMap = new HashMap<Integer, Serializable>();
    private Map<Integer, Serializable> cagesMap = new HashMap<Integer, Serializable>();
    private Map<Integer, Serializable> speciesMap = new HashMap<Integer, Serializable>();

    public void parseXML(InputStream is){

        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(is);
            Node root = document.getDocumentElement();
            NodeList speciesList = ((Element) ((Element) root).getElementsByTagName("species").item(0)).getElementsByTagName("entity");
            NodeList cagesList = ((Element) ((Element) root).getElementsByTagName("cages").item(0)).getElementsByTagName("entity");
            NodeList animalsList = ((Element) ((Element) root).getElementsByTagName("animals").item(0)).getElementsByTagName("entity");
            NodeList zookeepersList = ((Element) ((Element) root).getElementsByTagName("zookeepers").item(0)).getElementsByTagName("entity");

            for (int i = 0; i < speciesList.getLength(); i++){
                parseSpeciesNode(((Element) speciesList.item(i)));
            }
            for (int i = 0; i < zookeepersList.getLength(); i++){
                parseZookeeperNode((Element) zookeepersList.item(i));
            }
            for (int i = 0; i < cagesList.getLength(); i++){
                parseCageNode((Element) cagesList.item(i));
            }
            for (int i = 0; i < animalsList.getLength(); i++){
                parseAnimalNode((Element) animalsList.item(i));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void parseCageNode(Element cage){
        Serializable value = cageDAO.save(new Cage());
        if (cage.getElementsByTagName("localId").getLength()>0){
            cagesMap.put(Integer.parseInt(cage.getElementsByTagName("localId").item(0).getTextContent()), value);
        }
    }

    private void parseZookeeperNode(Element zookeeper){
        String firstName = zookeeper.getElementsByTagName("firstName").item(0).getTextContent();
        String lastName = zookeeper.getElementsByTagName("lastName").item(0).getTextContent();
        Serializable value = zookeeperDAO.save(new Zookeeper(firstName, lastName));
        if (zookeeper.getElementsByTagName("localId").getLength()>0){
            zookeepersMap.put(Integer.parseInt(zookeeper.getElementsByTagName("localId").item(0).getTextContent()), value);
        }
    }

    private void parseSpeciesNode(Element species){
        String name = species.getElementsByTagName("name").item(0).getTextContent();
        Serializable value = speciesDAO.save(new Species(name));
        if (species.getElementsByTagName("localId").getLength()>0){
            speciesMap.put(Integer.parseInt(species.getElementsByTagName("localId").item(0).getTextContent()), value);
        }
    }

    private void parseAnimalNode(Element animal){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String name = animal.getElementsByTagName("name").item(0).getTextContent();

        Node species = animal.getElementsByTagName("speciesId").item(0);
        int speciesId = new Integer(species.getTextContent());
        boolean isSpeciesIdLocal = (species.getAttributes().getNamedItem("idType").getNodeValue().equals("local") ? true : false);
        Species speciesObject = getSpeciesById(speciesId, isSpeciesIdLocal);

        Node cage = animal.getElementsByTagName("cageId").item(0);
        int cageId = new Integer(cage.getTextContent());
        boolean isCageIdLocal = (cage.getAttributes().getNamedItem("idType").getNodeValue().equals("local") ? true : false);
        Cage cageObject = getCageById(cageId, isCageIdLocal);

        Node zookeeper = animal.getElementsByTagName("zookeeperId").item(0);
        int zookeeperId = new Integer(zookeeper.getTextContent());
        boolean isZookeeperIdLocal = (zookeeper.getAttributes().getNamedItem("idType").getNodeValue().equals("local") ? true : false);
        Zookeeper zookeeperObject = getZookeeperById(zookeeperId, isZookeeperIdLocal);

        Date dateOfBirth = null;
        try {
            dateOfBirth = sdf.parse(animal.getElementsByTagName("dateOfBirth").item(0).getTextContent());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        animalDAO.save(new Animal(name, speciesObject, dateOfBirth, cageObject, zookeeperObject));
    }

    private Species getSpeciesById(int speciesId, boolean isSpeciesIdLocal){
        Serializable id = speciesId;
        if (isSpeciesIdLocal){
            id = speciesMap.get(speciesId);
        }
        return speciesDAO.get(Species.class, id);
    }

    private Cage getCageById(int cageId, boolean isCageIdLocal){
        Serializable id = cageId;
        if (isCageIdLocal){
            id = cagesMap.get(cageId);
        }
        return cageDAO.get(Cage.class, id);
    }

    private Zookeeper getZookeeperById(int zookeeperId, boolean isZookeeperIdLocal){
        Serializable id = zookeeperId;
        if (isZookeeperIdLocal){
            id = zookeepersMap.get(zookeeperId);
        }
        return zookeeperDAO.get(Zookeeper.class, id);
    }

    public ReaderXML(){
        speciesDAO = DBResourceManager.getSpeciesDAO();
        animalDAO = DBResourceManager.getAnimalDAO();
        cageDAO = DBResourceManager.getCageDAO();
        zookeeperDAO = DBResourceManager.getZookeeperDAO();
    }
}