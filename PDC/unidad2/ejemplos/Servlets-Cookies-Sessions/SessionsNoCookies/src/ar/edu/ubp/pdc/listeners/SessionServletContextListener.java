package ar.edu.ubp.pdc.listeners;

import java.util.EnumSet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.SessionTrackingMode;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class SessionServletContextListener
 *
 */
@WebListener
public class SessionServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SessionServletContextListener() { }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setSessionTrackingModes(EnumSet.of(SessionTrackingMode.URL));
   }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) { }
	
}
