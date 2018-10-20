package server;


import hibernate.dao.DBResourceManager;
import hibernate.dao.GenericDAOImpl;
import hibernate.entity.ZooControlSystemUser;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener{

    public void contextInitialized(ServletContextEvent arg0) {
        GenericDAOImpl<ZooControlSystemUser> dao = DBResourceManager.getUsersDAO();
        ZooControlSystemUser user = new ZooControlSystemUser("admin", "admin", true);
        dao.save(user);
    }
}