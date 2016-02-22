/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.intbit.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ilyas
 */
public class UserProgramUtility {

    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final Logger logger = Logger.getLogger(UserProgramUtility.class.getName());

    public static void generalProgramChecker() {
        int checkProgram = getGeneralProgram();
        if (checkProgram == 0) {
            logger.log(Level.INFO, "In UserProgramUtility: General Program does not exist in database");
            setGeneralProgram();
            logger.log(Level.INFO, "In UserProgramUtility: General Program added");
        } else if (checkProgram > 0) {
            logger.log(Level.INFO, "In UserProgramUtility: General Program already exists in database");
        } else if (checkProgram < 0) {
            logger.log(Level.INFO, "In UserProgramUtility: Something went wrong while saving General Program");
        }
    }

    public static int getGeneralProgram() {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("SELECT * FROM tbl_user_marketing_program where id =0;");
        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sbSql.toString())) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {
                int i = 0;
                while (resultSet.next()) {
                    i++;
                }
                return i;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            return -1;
        }

    }

    public static void setGeneralProgram() {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("INSERT INTO tbl_user_marketing_program(id, name, status) VALUES (0, 'General','Open');");
        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sbSql.toString())) {
            ps.execute();

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());

        }
    }
}
