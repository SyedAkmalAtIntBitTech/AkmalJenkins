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
public class FontStyles {

    SqlMethods sqlmethods;

    public FontStyles() throws NamingException {
        this.sqlmethods = new SqlMethods();
    }

    public boolean checkAvailability(String font_style) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        boolean check = false;
        try {
            query_string = "select * from tbl_font_style where font_style='" + font_style + "'";

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

    public void addFontSize(String font_style) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {
            query_string = "Insert into tbl_font_style (font_style) values(?)";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            prepared_statement.setString(1, font_style);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

    }

    public void editFontSize(Integer org_id, String font_style) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {
            query_string = "UPDATE tbl_font_style"
                    + " SET font_style='" + font_style + "'  WHERE id='" + org_id + "'";

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

    public void delete(Integer org_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {
            query_string = "Delete From tbl_font_style"
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
