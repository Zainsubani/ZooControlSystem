package server;

import hibernate.dao.DBResourceManager;
import hibernate.dao.GenericDAOImpl;
import hibernate.entity.ZooControlSystemUser;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

@WebServlet(
        name = "GetUsersServlet",
        urlPatterns = "/get_users"
)
public class GetUsersServlet extends HttpServlet {
    GenericDAOImpl<ZooControlSystemUser> dao = DBResourceManager.getUsersDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        Iterator<ZooControlSystemUser> iterator = dao.query("from ZooControlSystemUser", null).iterator();
        writer.write("{ \"users\" : [ ");
        boolean isCommaNeeded = false;
        while(iterator.hasNext()){
            writer.write((isCommaNeeded ? ", " : "") + iterator.next().toString());
            isCommaNeeded = true;
        }
        writer.write("] }");
    }
}
