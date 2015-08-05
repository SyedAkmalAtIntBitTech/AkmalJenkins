package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.organization;
import org.json.simple.JSONArray;

public class GetOrganizations extends BrndBotBaseHttpServlet {

    private static final long serialVersionUID = 1L;

    public GetOrganizations() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            PreparedStatement prepared_statement = null;
            ResultSet result_set = null;
            String organization_name = "";
            int organization_id = 1;
            JSONArray jsonarr = new JSONArray();

        try {

            String query = "Select * from tbl_organization";
            prepared_statement = getSqlMethodsInstance().getConnection().prepareStatement(query);
            result_set = prepared_statement.executeQuery();

            while (result_set.next()) {
                organization org = new organization();
                organization_name = result_set.getString("organization_name");
                organization_id = result_set.getInt("id");
                org.setId(organization_id);
                org.setOrganization_name(organization_name);
                jsonarr.add(org);
            }

            String json = new Gson().toJson(jsonarr);
            response.setContentType("application/json");
            out.write(json);
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            out.println(getSqlMethodsInstance().error);
        }finally {
            out.close();
            getSqlMethodsInstance().close(result_set, prepared_statement);
            getSqlMethodsInstance().closeConnection();
        }
    }

}
