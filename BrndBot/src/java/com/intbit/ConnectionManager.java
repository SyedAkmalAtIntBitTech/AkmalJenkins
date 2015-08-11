/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import com.controller.BrndBotBaseHttpServlet;
import com.controller.SqlMethods;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 *
 * @author Mohamed Sanaulla
 */
public class ConnectionManager {
    
    private static final ConnectionManager managerInstance 
            = new ConnectionManager();
    private static Logger logger = Logger.getLogger(ConnectionManager.class.getName());
    
    private DataSource dataSource;
    
    private ConnectionManager(){   
        Context envCtx;
        try {
            envCtx = (Context) new InitialContext().lookup("java:comp/env");
            dataSource = (DataSource) envCtx.lookup("jdbc/postgres");
        } catch (NamingException ex) {
            Logger.getLogger(SqlMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static ConnectionManager getInstance(){
        return managerInstance;
    }
    
    public Connection getConnection()throws SQLException{
        if ( dataSource == null ){
            throw new SQLException("Datasource not initialized");
        }
        
        BasicDataSource bdSource = (BasicDataSource)dataSource;
        logger.info("Active DB Connections : " + bdSource.getNumActive());
        logger.info("Idle DB Connections : " + bdSource.getNumIdle());
        return dataSource.getConnection();
    }
    
    public void closeConnection(Connection connection) throws SQLException{
        if ( connection != null ){
            connection.close();
        }
    }
}
