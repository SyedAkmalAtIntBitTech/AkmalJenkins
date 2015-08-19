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
public class Categories {

    private static final Logger logger = Logger.getLogger(Blocks.class.getName());

    SqlMethods sqlmethods;

    public Categories() throws NamingException {
        this.sqlmethods = new SqlMethods();
    }

    public boolean checkAvailability(String category, Integer organization) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        boolean check = false;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "select * from tbl_category where organization_id='" + organization + "' and category_name='" + category + "'";

            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                check = true;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }
        finally {
            sqlmethods.close(result_set, prepared_statement);
        }
        return check;
    }

    public Integer getOrganizationID(Integer category_id) {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        Integer Organization_id = 0;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select * from tbl_category where id=" + category_id + "";

            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                Organization_id = result_set.getInt("organization_id");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }finally {
            sqlmethods.close(result_set, prepared_statement);
        }

        return Organization_id;
    }

    public String getFileName(Integer category_id) {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        String fileName = "";
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select * from tbl_category where id=" + category_id + "";

            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                fileName = result_set.getString("image_name");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }finally {
            sqlmethods.close(result_set, prepared_statement);
        }

        return fileName;
    }

    public void addCategories(Integer organization_id, String category_name, String image_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Insert into tbl_category (organization_id, category_name, image_name) values(?,?,?)";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.setInt(1, organization_id);
            prepared_statement.setString(2, category_name);
            prepared_statement.setString(3, image_name);

            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            sqlmethods.close(result_set, prepared_statement);
        }

    }

    public void editCategories(Integer category_id, Integer organization_id, String category_name, String image_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "UPDATE tbl_category"
                    + " SET organization_id=" + organization_id + ", category_name='" + category_name + "', image_name='" + image_name + "' WHERE id='" + category_id + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            sqlmethods.close(result_set, prepared_statement);

        }

    }

    public void delete(Integer cat_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Delete From tbl_category"
                    + " WHERE id='" + cat_id + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            sqlmethods.close(result_set, prepared_statement);
        }

    }

}
