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
public class SubCategories {

    SqlMethods sqlmethods;

    public SubCategories() throws NamingException {
        this.sqlmethods = new SqlMethods();
    }

    public boolean checkAvailability(String external_source, String external_source_keyword, String sub_category_name, String category_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        boolean check = false;
        try {
            query_string = "select * from tbl_sub_category where external_source='" + external_source + "' and external_source_keyword='" + external_source_keyword + "' and sub_category_name='" + sub_category_name + "' and category_id='" + category_id + "'";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                check = true;
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            sqlmethods.close(result_set, prepared_statement);

        }

        return check;
    }

    public String getCategoryName(Integer category_id) {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        String category_name = "";
        try {
            query_string = "Select * from tbl_category where id=" + category_id + "";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                category_name = result_set.getString("category_name");
            }
            result_set.close();
            prepared_statement.close();

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            sqlmethods.close(result_set, prepared_statement);

        }

        return category_name;
    }

    public String getFileName(Integer category_id) {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        String fileName = "";
        try {
            query_string = "Select * from tbl_category where id=" + category_id + "";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                fileName = result_set.getString("image_name");
            }
            result_set.close();
            prepared_statement.close();

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            sqlmethods.close(result_set, prepared_statement);

        }

        return fileName;
    }

    public void addSubCategories(String external_source, String external_source_keyword, String sub_category_name, String category_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try {
            query_string = "Insert into tbl_sub_category (external_source, external_source_keyword, sub_category_name, category_id) values(?,?,?,?)";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            prepared_statement.setString(1, external_source);
            prepared_statement.setString(2, external_source_keyword);
            prepared_statement.setString(3, sub_category_name);
            prepared_statement.setString(4, category_id);

            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            sqlmethods.close(result_set, prepared_statement);

        }

    }

    public void editSubCategories(Integer sub_category_id, String external_source, String external_source_keyword, String sub_category_name, String category_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try {
            query_string = "UPDATE tbl_sub_category"
                    + " SET external_source='" + external_source + "', external_source_keyword='" + external_source_keyword + "', sub_category_name='" + sub_category_name + "', category_id='" + category_id + "' WHERE id='" + sub_category_id + "'";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            sqlmethods.close(result_set, prepared_statement);

        }

    }

    public void delete(Integer cat_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try {
            query_string = "Delete From tbl_sub_category"
                    + " WHERE id='" + cat_id + "'";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            sqlmethods.close(result_set, prepared_statement);

        }

    }

}
