package server;

import hibernate.dao.DBResourceManager;
import hibernate.dao.GenericDAOImpl;
import hibernate.entity.Animal;
import hibernate.entity.Cage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

@WebServlet(
        name = "GetAnimalsInCage",
        urlPatterns = "/getanimalsincage"
)
public class GetAnimalsInCageServlet extends HttpServlet {
    private GenericDAOImpl<Cage> cageDAO = DBResourceManager.getCageDAO();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        Iterator<Animal> animalIterator = cageDAO.get(Cage.class, id).getAnimalsInCage().iterator();
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write("{ animalsInCage : [ ");
        boolean isCommaNeeded = false;
        while(animalIterator.hasNext()){
            writer.write((isCommaNeeded ? ", " : "") + animalIterator.next().toString());
            isCommaNeeded = true;
        }
        writer.write("] }");
    }
}
