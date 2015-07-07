package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import static com.controller.SqlMethods.con;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.organization;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GetOrganizations extends HttpServlet {

    private static final long serialVersionUID = 1L;
    SqlMethods sqlmethods = new SqlMethods();
    PreparedStatement prepared_statement;
    ResultSet result_set;
    String organization_name = "";
    int organization_id = 1;
    JSONArray jsonarr = new JSONArray();

    public GetOrganizations() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();

        try {
            sqlmethods.setDatabaseConnection();

            String query = "Select * from tbl_organization";
            prepared_statement = con.prepareStatement(query);
            result_set = prepared_statement.executeQuery();

            while (result_set.next()) {
                organization org = new organization();
                organization_name = result_set.getString("organization_name");
                organization_id = result_set.getInt("id");
                org.setId(organization_id);
                org.setOrganization_name(organization_name);
                jsonarr.add(org);
            }

            result_set.close();
            prepared_statement.close();


            String json = new Gson().toJson(jsonarr);
            response.setContentType("application/json");
            out.write(json);
            sqlmethods.con.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            out.println(sqlmethods.error);
        }finally {
            out.close();
        }
    }

}
