package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import static com.controller.sqlMethods.con;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.organization;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class getOrganizations extends HttpServlet {
    private static final long serialVersionUID = 1L;
    sqlMethods SM = new sqlMethods();
    
    public getOrganizations() {
        super();
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String ORG_name = "";
        
        int ORG_id = 1;
        
//        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        
        try{
            PreparedStatement ps;
            ResultSet rs;
            SM.setConnection();
        
            String Query = "Select * from tbl_organization";
            ps = con.prepareStatement(Query);
            rs = ps.executeQuery();

            while (rs.next()){
                    organization  org = new organization();
                    ORG_name = rs.getString("organization_name");
                    ORG_id = rs.getInt ("id");
                    org.setId(ORG_id);
                    org.setOrganization_name(ORG_name);
                    arr.add(org);
            }
            
            rs.close();
            ps.close();
        
            StringWriter out = new StringWriter();

        //      obj.writeJSONString(out);
//              System.out.println(obj);
        //      String jsonText = out.toString();
      //        String jsonText = obj.toJSONString();        
              String json = new Gson().toJson(arr);
              response.setContentType("application/json");
              response.getWriter().write(json);
              }catch(Exception e){}
        }

}
