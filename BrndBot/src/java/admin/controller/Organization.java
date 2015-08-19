/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.SqlMethods;
import com.intbit.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author intbit
 */
public class Organization {
    private static final Logger logger = Logger.getLogger(Organization.class.getName());
    SqlMethods sqlmethods;

    public Organization() throws NamingException {
        this.sqlmethods = new SqlMethods();
    }

    public boolean checkAvailability(String organization_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        boolean check = false;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "select * from tbl_organization where organization_name='" + organization_name + "'";

            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                check = true;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

        return check;
    }

    public void addOrganization(String organization_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Insert into tbl_organization (organization_name) values(?)";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.setString(1, organization_name);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

    }

    public void changeOrganization(Integer org_id, String organization_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "UPDATE tbl_organization"
                    + " SET organization_name='" + organization_name + "'  WHERE id='" + org_id + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

    }

    public void deleteOrganization(Integer org_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Delete From tbl_organization"
                    + " WHERE id='" + org_id + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

    }

}
