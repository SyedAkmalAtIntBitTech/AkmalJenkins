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
public class Blocks {
    SqlMethods sqlmethods;

    public Blocks() throws NamingException {
        this.sqlmethods = new SqlMethods();
    }

    public boolean checkAvailability(String name) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        boolean check = false;
        try {
            query_string = "select * from tbl_blocks where name='" + name + "'";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                check = true;
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        finally {
            
            sqlmethods.close(result_set, prepared_statement);
        }

        return check;
    }

    public void addBlock(String name, String mindbody_query, Integer brand_id, Integer sub_category_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try {
            query_string = "Insert into tbl_blocks (name, mindbody_query, brand_id, sub_category_id) values(?, ?, ?, ?)";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            prepared_statement.setString(1, name);
            prepared_statement.setString(2, mindbody_query);
            prepared_statement.setInt(3, brand_id);
            prepared_statement.setInt(4, sub_category_id);
            
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        finally {
            sqlmethods.close(result_set, prepared_statement);
        }

    }

    public void changeBlock(Integer id, String name, String mindbody_query, Integer brand_id, Integer sub_category_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try {
            query_string = "UPDATE tbl_blocks"
                    + " SET name='" + name + "', mindbody_query='"+mindbody_query+"', brand_id="+brand_id+", sub_category_id="+sub_category_id+"  WHERE id=" + id + "";
            
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

    public void deleteBlock(Integer block_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        try {
            query_string = "Delete From tbl_blocks"
                    + " WHERE id='" + block_id + "'";

            prepared_statement = sqlmethods.getConnection().prepareStatement(query_string);
            prepared_statement.executeUpdate();
            prepared_statement.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        finally {
             sqlmethods.close(result_set, prepared_statement);
        }

    }
    
}
