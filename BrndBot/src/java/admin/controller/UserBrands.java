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
 * @author development
 */
public class UserBrands {
    private static final Logger logger = Logger.getLogger(Organization.class.getName());
    SqlMethods sqlmethods;

    public UserBrands() throws NamingException {
        this.sqlmethods = new SqlMethods();
    }

    public boolean checkAvailability(Integer organization_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        boolean check = false;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "select * from tbl_user_brands where organization_name='" + organization_id + "'";

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

    public void addUserBrands(Integer user_id, Integer brand_id, Integer organization_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Insert into tbl_user_brands (user_id, brand_id, organization) values(?, ?, ?)";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.setInt(1, user_id);
            prepared_statement.setInt(2, brand_id);
            prepared_statement.setInt(3, organization_id);
            
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            sqlmethods.close(result_set, prepared_statement);
        }

    }

    public void changeUserBrands(Integer user_brand_id, 
                                 String user_id, String brand_id, 
                                 String organization) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "UPDATE tbl_user_brands"
                    + " SET user_id ='"+ user_id +"', brand_id='"+ brand_id +"',"
                    +       "organization='" + organization + "'"
                    + "WHERE id='" + user_brand_id + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            sqlmethods.close(result_set, prepared_statement);
        }

    }

    public void deleteUserBrand(Integer user_brand_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Delete From tbl_user_brands"
                    + " WHERE id='" + user_brand_id + "'";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        } finally {
            sqlmethods.close(result_set, prepared_statement);
        }

    }
    
}
