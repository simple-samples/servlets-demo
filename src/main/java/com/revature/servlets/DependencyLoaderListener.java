package com.revature.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
This class implements the ServletContextListener interface from the javax.servlet package.
The purpose is to offer two overridden methods from that parent that hook into your code.
The implementations written below will occur once each, contextInitialized() at startup and
contextDestroyed() at shutdown of the servlet container.
 */
public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context initializing...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context destroying...");
    }
}