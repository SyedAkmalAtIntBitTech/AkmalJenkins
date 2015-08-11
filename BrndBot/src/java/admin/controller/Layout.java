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
public class Layout {

    public SqlMethods sqlmethods;

    public Layout() {
        this.sqlmethods = new SqlMethods();
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
        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Select * from tbl_brand_personality where id=" + brand_id + "";

            prepared_statement = connection.prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            if (result_set.next()) {
                fileName = result_set.getString("image");
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

        return fileName;
    }

    public void addLayouts(Integer organization_id, Integer user_id, Integer category_id, String layout, String model, boolean email, boolean social, Integer sub_category_id, Integer brand_id, Integer block_id) throws SQLException {
        String query_string = "";
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {
            query_string = "Insert into tbl_model (organization_id, user_id, category_id, layout_file_name, model_file_name, email, social, sub_category_id, brand_id, block_id) values(?,?,?,?,?,?,?,?,?,?)";

            prepared_statement = connection.prepareStatement(query_string);
            prepared_statement.setInt(1, organization_id);
            prepared_statement.setInt(2, user_id);
            prepared_statement.setInt(3, category_id);
            prepared_statement.setString(4, layout);
            prepared_statement.setString(5, model);
            prepared_statement.setBoolean(6, email);
            prepared_statement.setBoolean(7, social);
            prepared_statement.setInt(8, sub_category_id);
            prepared_statement.setInt(9, brand_id);
            prepared_statement.setInt(10, block_id);

            prepared_statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
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
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
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
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }        finally {
                        sqlmethods.close(result_set, prepared_statement);

        }

    }

}
