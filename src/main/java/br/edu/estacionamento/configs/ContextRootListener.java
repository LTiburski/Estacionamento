/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.estacionamento.configs;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Lucas Fernando
 */
public class ContextRootListener implements ServletContextListener{
     /////////////////////////////////////////////////////////////
    // Contexto inicializado pelo arquivo "web.xml" em WEB-INF //
   ///////////////////////////////////////////// ///////////////
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextInfo.setContextPath(sce.getServletContext().getContextPath());
        ServletContextInfo.setRealPath(sce.getServletContext().getRealPath("/"));
        ServletContextInfo.setFullServerName(sce.getServletContext().getInitParameter("fullServerName"));
        ServletContextInfo.setDriver(sce.getServletContext().getInitParameter("driver"));
        ServletContextInfo.setUsername(sce.getServletContext().getInitParameter("username"));
        ServletContextInfo.setPassword(sce.getServletContext().getInitParameter("password"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Não suportado .");
    }

}
