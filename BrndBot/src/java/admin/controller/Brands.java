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
public class Brands {
    private static final Logger logger = Logger.getLogger(Blocks.class.getName());
    
    SqlMethods sqlmethods;

    public Brands() throws NamingException {
        sqlmethods = new SqlMethods();
    }

    public boolean checkAvailability(String brand_name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        boolean check = false;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "select * from tbl_brand_personality where brand_name='" + brand_name + "'";

            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                check = true;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            sqlmethods.close(result_set, prepared_statement);
        }
        return check;
    }

    public String getFileName(Integer brand_id) {
        String fileName = "";
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select * from tbl_brand_personality where id=" + brand_id + "";

            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                fileName = result_set.getString("image");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }finally {
            sqlmethods.close(result_set, prepared_statement);
        }

        return fileName;
    }

    public void addBrands(String brand_name, Integer look_id, String image) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Insert into tbl_brand_personality (brand_name, look_id, image) values(?,?,?)";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.setString(1, brand_name);
            prepared_statement.setInt(2, look_id);
            prepared_statement.setString(3, image);

            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }finally {
            sqlmethods.close(result_set, prepared_statement);
        }

    }

    public void editBrands(Integer brand_id, String brand_name, Integer look_id, String image) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "UPDATE tbl_brand_personality"
                    + " SET brand_name='" + brand_name + "', look_id=" + look_id + ", image='" + image + "'  WHERE id='" + brand_id + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }finally {
            sqlmethods.close(result_set, prepared_statement);
        }

    }

    public void deleteBrands(Integer org_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Delete From tbl_brand_personality"
                    + " WHERE id='" + org_id + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }finally {
            sqlmethods.close(result_set, prepared_statement);
        }

    }

}
