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
import java.sql.Statement;
import javax.naming.NamingException;

/**
 *
 * @author intbit
 */
public class ColorThemes {

    SqlMethods sqlmethods;

    public ColorThemes() throws NamingException {
        this.sqlmethods = new SqlMethods();
    }

    public boolean checkAvailability(String brand_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        boolean check = false;
        try {
            query_string = "select * from tbl_brand_color_theme where brand_name='" + brand_name + "'";

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

    public String getFileName(Integer brand_id) {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        String fileName = "";
        try {
            query_string = "Select * from tbl_brand_personality where id=" + brand_id + "";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                fileName = result_set.getString("image");
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

    public String getThemeNumber() {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        String theme = "theme";
        try {
            query_string = "select theme_name from tbl_brand_color_theme";

            Statement sta = sqlmethods.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // Catch the ResultSet object
            result_set = sta.executeQuery(query_string);

            // Check ResultSet's scrollability
            if (result_set.getType() == ResultSet.TYPE_FORWARD_ONLY) {
                System.out.println("ResultSet non-scrollable.");
            } else {
                System.out.println("ResultSet scrollable.");
            }
//            result_set = prepared_statement.executeQuery();
//            
            if (result_set.last() == false) {
                return theme + "1";
            } else {
                String id = result_set.getString(1);
                String EID = id.substring(5);
                int NID = Integer.parseInt(EID) + 1;
                theme = theme + NID;
            }
            result_set.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

        return theme;
    }

    public void addColorTheme(Integer brand_id, String color1, String color2, String color3, String color4, String color5, String color6, String theme) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try {
            query_string = "Insert into tbl_brand_color_theme (brand_id, color1, color2, color3, color4, color5, color6, predefined, theme_name) values(?,?,?,?,?,?,?,?,?)";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            prepared_statement.setInt(1, brand_id);
            prepared_statement.setString(2, color1);
            prepared_statement.setString(3, color2);
            prepared_statement.setString(4, color3);
            prepared_statement.setString(5, color4);
            prepared_statement.setString(6, color5);
            prepared_statement.setString(7, color6);
            prepared_statement.setBoolean(8, true);
            prepared_statement.setString(9, theme);

            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

    }

    public void editTheme(Integer theme_id, Integer brand_id, String color1, String color2, String color3, String color4, String color5, String color6) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try {
            query_string = "UPDATE tbl_brand_color_theme"
                    + " SET brand_id='" + brand_id + "', color1=" + color1 + ",color2=" + color2 + ",color3=" + color3 + ",color4=" + color4 + ",color5=" + color5 + ",color6=" + color6 + "  WHERE id='" + theme_id + "'";

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

    public void delete(Integer color_theme_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try {
            query_string = "Delete From tbl_brand_color_theme"
                    + " WHERE id='" + color_theme_id + "'";

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
