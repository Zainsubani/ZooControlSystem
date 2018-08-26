package server;


import hibernate.dao.DBResourceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener{

    public void contextInitialized(ServletContextEvent arg0) {
        DBResourceManager.getAnimalDAO();
    }
}