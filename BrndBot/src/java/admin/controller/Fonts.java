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
public class Fonts {

    SqlMethods sqlmethods;

    public Fonts() throws NamingException {
        this.sqlmethods = new SqlMethods();
    }

    public boolean checkAvailability(String font_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        boolean check = false;
        try {
            query_string = "select * from tbl_font_family where font_name='" + font_name + "'";

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

    public String getFileName(Integer font_id) {
        String fileName = "";
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {
            query_string = "Select * from tbl_font_family where id=" + font_id + "";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                fileName = result_set.getString("file_name");
            }
            result_set.close();
            prepared_statement.close();

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

        return fileName;
    }

    public void addFont(String font_name, String file_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {
            query_string = "Insert into tbl_font_family (font_name, file_name) values(?,?)";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            prepared_statement.setString(1, font_name);
            prepared_statement.setString(2, file_name);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

    }

    public void changeFont(Integer font_id, String font_name, String file_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try {
            query_string = "UPDATE tbl_font_family"
                    + " SET font_name='" + font_name + "', file_name='" + file_name + "'  WHERE id='" + font_id + "'";

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
            query_string = "Delete From tbl_font_family"
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
