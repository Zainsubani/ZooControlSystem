package server;

import hibernate.dao.DBResourceManager;
import hibernate.dao.GenericDAOImpl;
import hibernate.entity.Animal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "GetAnimalById",
        urlPatterns = "/getanimalbyid"
)
public class GetAnimalByIdServlet extends HttpServlet {

    private GenericDAOImpl<Animal> animalDAO = DBResourceManager.getAnimalDAO();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        Animal animal = animalDAO.get(Animal.class, id);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(animal.toString());
        writer.flush();
    }
}