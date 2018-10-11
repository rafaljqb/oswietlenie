/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oswietlenie;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 *
 * @author rjaku
 */
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PanelSocketSerwer.getInstance().start();
    }
 
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        PanelSocketSerwer.getInstance().stop();
    }
}
