/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Event Listener writes container managed events to log file.
 * @author MATHAF
 */
public class EventListener implements ServletContextListener,
                                      HttpSessionAttributeListener, HttpSessionListener
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( EventListener.class );

    /**
     * The servlet context with which we are associated.
     */
    private ServletContext context = null;

    /**
     * Record the fact that a servlet context attribute was added.
     *
     * @param event The session attribute event
     */
    @Override
    public void attributeAdded( HttpSessionBindingEvent event )
    {

        log( "attributeAdded('" + event.getSession().getId() + "', '"
        + event.getName() + "', '" + event.getValue() + "')" );

    }

    /**
     * Record the fact that a servlet context attribute was removed.
     *
     * @param event The session attribute event
     */
    @Override
    public void attributeRemoved( HttpSessionBindingEvent event )
    {

        log( "attributeRemoved('" + event.getSession().getId() + "', '"
        + event.getName() + "', '" + event.getValue() + "')" );

    }

    /**
     * Record the fact that a servlet context attribute was replaced.
     *
     * @param event The session attribute event
     */
    @Override
    public void attributeReplaced( HttpSessionBindingEvent event )
    {

        log( "attributeReplaced('" + event.getSession().getId() + "', '"
        + event.getName() + "', '" + event.getValue() + "')" );

    }

    /**
     * Record the fact that this web application has been destroyed.
     *
     * @param event The servlet context event
     */
    @Override
    public void contextDestroyed( ServletContextEvent event )
    {

        log( "contextDestroyed()" );
        this.context = null;

    }

    /**
     * Record the fact that this web application has been initialized.
     *
     * @param event The servlet context event
     */
    @Override
    public void contextInitialized( ServletContextEvent event )
    {

        this.context = event.getServletContext();
        log( "contextInitialized()" );

    }

    /**
     * Record the fact that a session has been created.
     *
     * @param event The session event
     */
    @Override
    public void sessionCreated( HttpSessionEvent event )
    {

        log( "sessionCreated('" + event.getSession().getId() + "')" );

    }

    /**
     * Record the fact that a session has been destroyed.
     *
     * @param event The session event
     */
    @Override
    public void sessionDestroyed( HttpSessionEvent event )
    {

        log( "sessionDestroyed('" + event.getSession().getId() + "')" );

    }

    /**
     * Log a message to the application log.
     *
     * @param message Message to be logged
     */
    private void log( String message )
    {
        LOG.info( "EventListener: {}", message );
    }
}
