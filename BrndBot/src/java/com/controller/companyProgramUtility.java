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
public class companyProgramUtility {

    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final Logger logger = Logger.getLogger(companyProgramUtility.class.getName());

    public static void generalProgramChecker() {
        int checkProgram = getGeneralProgram();
        if (checkProgram == 0) {
            logger.log(Level.INFO, "In companyProgramUtility: General Program does not exist in database");
            setGeneralProgram();
            logger.log(Level.INFO, "In companyProgramUtility: General Program added");
        } else if (checkProgram > 0) {
            logger.log(Level.INFO, "In companyProgramUtility: General Program already exists in database");
        } else if (checkProgram < 0) {
            logger.log(Level.INFO, "In companyProgramUtility: Something went wrong while selecting General Program");
        }
    }

    public static int getGeneralProgram() {
        StringBuilder sbSql = new StringBuilder();
        int programCount = 0;
        sbSql.append("SELECT count(*) FROM company_marketing_program where company_marketing_program_id = 0;");
        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sbSql.toString())) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {
                resultSet.next();
                programCount = resultSet.getInt(1);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            programCount = -1;
        } finally {
            return programCount;
        }

    }

    public static void setGeneralProgram() {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("INSERT INTO company_marketing_program(company_marketing_program_id, company_marketing_program_name, status) VALUES (0, 'General','Open');");
        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sbSql.toString())) {
            ps.execute();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }
}
