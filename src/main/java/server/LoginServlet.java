package server;

import hibernate.dao.DBResourceManager;
import hibernate.dao.GenericDAOImpl;
import hibernate.entity.ZooControlSystemUser;
import security.Passwords;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login").toLowerCase();
        String pass = request.getParameter("pass");
        GenericDAOImpl<ZooControlSystemUser> dao = DBResourceManager.getUsersDAO();
        ZooControlSystemUser user = dao.get(ZooControlSystemUser.class, login);
        byte[] hash = user.getHash();
        byte[] salt = user.getSalt();

        if (Passwords.isExpectedPassword(pass.toCharArray(), salt, hash)){
            request.getSession().setAttribute("user", login);
            response.sendRedirect(request.getContextPath() + "/testpage");
        }
    }
}