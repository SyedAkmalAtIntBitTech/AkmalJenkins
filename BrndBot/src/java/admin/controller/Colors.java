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
import javax.naming.NamingException;

/**
 *
 * @author intbit
 */
public class Colors {

    SqlMethods sqlmethods;

    public Colors() throws NamingException {
        this.sqlmethods = new SqlMethods();
    }

    public boolean checkAvailability(String color_hex, String color_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        boolean check = false;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "select * from tbl_colors where color_hex='" + color_hex + "' and color_name='" + color_name + "'";

            prepared_statement = connection.prepareStatement(query_string);
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

    public void add(String color_hex, String color_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Insert into tbl_colors (color_hex, color_name) values(?,?)";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.setString(1, color_hex);
            prepared_statement.setString(2, color_name);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

    }

    public void edit(Integer org_id, String color_hex, String color_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "UPDATE tbl_colors"
                    + " SET color_hex='" + color_hex + "', color_name='" + color_name + "'  WHERE id='" + org_id + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

    }

    public void delete(Integer org_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Delete From tbl_colors"
                    + " WHERE id='" + org_id + "'";

            prepared_statement = connection.prepareStatement(query_string);
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
