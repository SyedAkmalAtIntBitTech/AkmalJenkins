/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import com.controller.SqlMethods;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author intbit
 */
public class Organization {

    SqlMethods sqlmethods;

    public Organization() throws NamingException {
        this.sqlmethods = new SqlMethods();
    }

    public boolean checkAvailability(String organization_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        boolean check = false;
        try {
            query_string = "select * from tbl_organization where organization_name='" + organization_name + "'";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                check = true;
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

        return check;
    }

    public void addOrganization(String organization_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try {
            query_string = "Insert into tbl_organization (organization_name) values(?)";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            prepared_statement.setString(1, organization_name);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

    }

    public void changeOrganization(Integer org_id, String organization_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try {
            query_string = "UPDATE tbl_organization"
                    + " SET organization_name='" + organization_name + "'  WHERE id='" + org_id + "'";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

    }

    public void deleteOrganization(Integer org_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try {
            query_string = "Delete From tbl_organization"
                    + " WHERE id='" + org_id + "'";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

    }

}
