package server;

import hibernate.dao.DBResourceManager;
import hibernate.dao.GenericDAOImpl;
import hibernate.entity.Animal;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(
        name = "GetAnimals",
        urlPatterns = "/getanimals"
)
public class GetAnimalsServlet extends HttpServlet {
    private GenericDAOImpl<Animal> animalDAO = DBResourceManager.getAnimalDAO();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write("{ \"animals\" : [ ");
        List<Animal> animals = animalDAO.query("from Animal", null);
        boolean isCommaNeeded = false;
        for (Animal animal : animals){
            writer.write((isCommaNeeded ? ", " : "") + animal);
            isCommaNeeded = true;
        }
        writer.write("] }");
        writer.flush();
    }
}
