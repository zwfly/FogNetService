package com.yrsd.fognet.device.access; /**
 * Created by admin on 2017/6/17.
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class ListenerDevice implements ServletContextListener {

    // Public constructor is required by servlet spec
    public ListenerDevice() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("contextInitialized");
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

}
