package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import hibernate.dao.DBResourceManager;
import hibernate.dao.GenericDAOImpl;
import hibernate.entity.ZooControlSystemUser;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "AdminServlet",
        urlPatterns = "/admin.do"
)
public class AdminServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        GenericDAOImpl<ZooControlSystemUser> dao = DBResourceManager.getUsersDAO();
        String action = request.getParameter("action");
        String login = request.getParameter("login");
        String data = request.getParameter("data");
        ZooControlSystemUser user;
        JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
        if (!dao.get(ZooControlSystemUser.class, request.getSession(false).getAttribute("user").toString()).isAdmin())
            return;
        switch (action) {
            case "register_user":
                user = new ZooControlSystemUser(login, jsonObject.get("pass").getAsString(),
                        jsonObject.get("is_admin").getAsBoolean());
                dao.save(user);
                break;
            case "delete_user":
                user = dao.get(ZooControlSystemUser.class, login);
                dao.delete(user);
                break;
            case "change_role":
                user = dao.get(ZooControlSystemUser.class, login);
                user.setAdmin(jsonObject.get("is_admin").getAsBoolean());
                break;
        }
    }
}
