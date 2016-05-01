package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.intbit.ConnectionManager;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import model.organization;
import org.json.simple.JSONArray;

public class GetOrganizations extends BrndBotBaseHttpServlet {

    private static final long serialVersionUID = 1L;

    public GetOrganizations() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            super.processRequest(request, response);
            PrintWriter out = response.getWriter();
            PreparedStatement prepared_statement = null;
            ResultSet result_set = null;
            String organization_name = "";
            int organization_id = 1;
            JSONArray jsonarr = new JSONArray();

        try(Connection connection = ConnectionManager.getInstance().getConnection()) {

            String query = "Select * from organization";
            
            prepared_statement = connection.prepareStatement(query);
            result_set = prepared_statement.executeQuery();

            while (result_set.next()) {
                organization org = new organization();
                organization_name = result_set.getString("organization_name");
                organization_id = result_set.getInt("organization_id");
                org.setId(organization_id);
                org.setOrganization_name(organization_name);
                jsonarr.add(org);
            }

            String json = new Gson().toJson(jsonarr);
            response.setContentType("application/json");
            out.write(json);
        } catch (Exception e) {
                       logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));

            out.println(getSqlMethodsInstance().error);
        }finally {
            out.close();
            getSqlMethodsInstance().close(result_set, prepared_statement);
        }
    }

}
