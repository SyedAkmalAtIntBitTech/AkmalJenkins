/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.dao;

import com.intbit.ConnectionManager;
import com.intbit.ScheduledEntityType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author development
 */
// To-Do Ilyas/AR refactor or remove ??
public class CheckTemplates {

    private static final Logger logger = Logger.getLogger(CheckTemplates.class.getName());

    public Integer checkTemplates(Integer category_id, Integer sub_category_id, String type, Integer user_id) throws SQLException {
        String query_string = "";
        Integer Number = 0;
        try (Connection conn = ConnectionManager.getInstance().getConnection()) {
            if (type.equalsIgnoreCase(ScheduledEntityType.Email.toString())) {
                query_string = "Select Count(*) from tbl_model where category_id=?"
                        + " and sub_category_id=? and email=? and (user_id=" + user_id + " or user_id=0)";
                try (PreparedStatement prepared_statement = conn.prepareStatement(query_string)) {
                    prepared_statement.setInt(1, category_id);
                    prepared_statement.setInt(2, sub_category_id);
                    prepared_statement.setBoolean(3, true);
                    try (ResultSet result_set = prepared_statement.executeQuery()) {
                        if (result_set.next()) {
                            Number = result_set.getInt(1);
                        }
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "Error caused while checking the saved template", e);
                    }
                }
            } else if (type.equalsIgnoreCase("social")) {
                query_string = "Select Count(*) from tbl_model where category_id=?"
                        + " and sub_category_id=? and social=? and media=? and (user_id=" + user_id + " or user_id=0)";
                try (PreparedStatement prepared_statement = conn.prepareStatement(query_string)) {
                    prepared_statement.setInt(1, category_id);
                    prepared_statement.setInt(2, sub_category_id);
                    prepared_statement.setBoolean(3, true);
                    prepared_statement.setBoolean(4, true);
                    try (ResultSet result_set = prepared_statement.executeQuery()) {
                        if (result_set.next()) {
                            Number = result_set.getInt(1);
                        }
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "Error caused while checking the saved template", e);
                    }
                }
            } else if (type.equalsIgnoreCase("print")) {
                query_string = "Select Count(*) from tbl_model where category_id=?"
                        + " and sub_category_id=? and social=? and print=? and (user_id=" + user_id + " or user_id=0)";
                try (PreparedStatement prepared_statement = conn.prepareStatement(query_string)) {
                    prepared_statement.setInt(1, category_id);
                    prepared_statement.setInt(2, sub_category_id);
                    prepared_statement.setBoolean(3, true);
                    prepared_statement.setBoolean(4, true);
                    try (ResultSet result_set = prepared_statement.executeQuery()) {
                        if (result_set.next()) {
                            Number = result_set.getInt(1);
                        }
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "Error caused while checking the saved template", e);
                    }
                }
            } else if (type.equalsIgnoreCase("download")) {
                query_string = "Select Count(*) from tbl_model where category_id=?"
                        + " and sub_category_id=? and social=? and download=? and (user_id=" + user_id + " or user_id=0)";
                try (PreparedStatement prepared_statement = conn.prepareStatement(query_string)) {
                    prepared_statement.setInt(1, category_id);
                    prepared_statement.setInt(2, sub_category_id);
                    prepared_statement.setBoolean(3, true);
                    prepared_statement.setBoolean(4, true);
                    try (ResultSet result_set = prepared_statement.executeQuery()) {
                        if (result_set.next()) {
                            Number = result_set.getInt(1);
                        }
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "Error caused while checking the saved template", e);
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error caused while checking the saved template", e);
        }
        return Number;
    }

}
