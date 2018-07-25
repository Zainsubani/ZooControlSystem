package xml;

import hibernate.dao.*;
import hibernate.entity.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

public class WriterXML {
    private GenericDAOImpl<Animal> animalDAO;
    private GenericDAOImpl<Cage> cageDAO;
    private GenericDAOImpl<Species> speciesDAO;
    private GenericDAOImpl<Zookeeper> zookeeperDAO;

    public void writeXML(File file){
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("enteties");
        doc.appendChild(rootElement);
        Element zookeepers = doc.createElement("zookeepers");
        Element cages = doc.createElement("cages");
        Element species = doc.createElement("species");
        Element animals = doc.createElement("animals");
        rootElement.appendChild(zookeepers);
        rootElement.appendChild(cages);
        rootElement.appendChild(species);
        rootElement.appendChild(animals);

        List<Zookeeper> zookeepersList = zookeeperDAO.query("from Zookeeper", null);
        for (Zookeeper zookeeper : zookeepersList){
            Element zookeeperElement = doc.createElement("entity");
            Element firstNameElement = doc.createElement("firstName");
            Element lastNameElement = doc.createElement("lastName");
            Element localIdElement = doc.createElement("localId");
            localIdElement.setTextContent(zookeeper.getId().toString());
            firstNameElement.setTextContent(zookeeper.getFirstName());
            lastNameElement.setTextContent(zookeeper.getLastName());
            zookeeperElement.appendChild(firstNameElement);
            zookeeperElement.appendChild(lastNameElement);
            zookeeperElement.appendChild(localIdElement);
            zookeepers.appendChild(zookeeperElement);
        }

        List<Cage> cagesList = cageDAO.query("from Cage", null);
        for (Cage cage : cagesList){
            Element cageElement= doc.createElement("entity");
            Element localIdElement = doc.createElement("localId");
            localIdElement.setTextContent(cage.getId().toString());
            cageElement.appendChild(localIdElement);
            cages.appendChild(cageElement);
        }

        List<Species> speciesList = speciesDAO.query("from Species", null);
        for (Species speciesObj : speciesList){
            Element speciesElement = doc.createElement("entity");
            Element nameElement = doc.createElement("name");
            Element localIdElement = doc.createElement("localId");
            nameElement.setTextContent(speciesObj.getName());
            localIdElement.setTextContent(speciesObj.getId().toString());
            speciesElement.appendChild(nameElement);
            speciesElement.appendChild(localIdElement);
            species.appendChild(speciesElement);
        }

        List<Animal> animalsList = animalDAO.query("from Animal", null);
        for (Animal animal : animalsList){
           Element animalElement = doc.createElement("entity");
           Element nameElement = doc.createElement("name");
           Element dateOfBirthElement = doc.createElement("dateOfBirth");
           Element zookeeperElement = doc.createElement("zookeeperId");
           Element cageElement = doc.createElement("cageId");
           Element speciesElement = doc.createElement("speciesId");
           nameElement.setTextContent(animal.getName());
           SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
           dateOfBirthElement.setTextContent(sdf.format(animal.getDateOfBirth()));
           zookeeperElement.setTextContent(animal.getZookeeper().getId().toString());
           zookeeperElement.setAttribute("idType", "local");
           speciesElement.setTextContent(animal.getSpecies().getId().toString());
           speciesElement.setAttribute("idType", "local");
           cageElement.setTextContent(animal.getCage().getId().toString());
           cageElement.setAttribute("idType", "local");
           animalElement.appendChild(nameElement);
           animalElement.appendChild(dateOfBirthElement);
           animalElement.appendChild(zookeeperElement);
           animalElement.appendChild(speciesElement);
           animalElement.appendChild(cageElement);
           animals.appendChild(animalElement);
        }


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);

        //StreamResult result = new StreamResult(System.out);

        try {
            Transformer  transformer = transformerFactory.newTransformer();
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public WriterXML(){
        speciesDAO = DBResourceManager.getSpeciesDAO();
        animalDAO = DBResourceManager.getAnimalDAO();
        cageDAO = DBResourceManager.getCageDAO();
        zookeeperDAO = DBResourceManager.getZookeeperDAO();
    }
}
