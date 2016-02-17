<%-- 
    Document   : displayfonts
    Created on : Jul 6, 2015, 6:11:32 PM
    Author     : intbit
--%>

<%@page import="com.intbit.ConnectionManager"%>
<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.*"%>
<%@include file="checksession.jsp" %>

<%!   
    Logger logger = Logger.getLogger("displayfonts.jsp");
    //SqlMethods SM = new SqlMethods();
    String buffer="";
    String buffer1 = "";
    String buffer2 = "";
    
%>

<%  
    Connection conn = null;
    try {
        Integer Brand_id = Integer.parseInt(request.getParameter("Brand_id"));
        
            buffer="<select id='brand'><option value='0'>Select</option>";
            buffer1 = "<select id='fontnames'><option value='none'>Select</option>";
            buffer2 = "<select id='fontsizes'><option value='none'>Select</option>";
            conn = ConnectionManager.getInstance().getConnection();
            Statement stmt = null;
            ResultSet rs = null;
//            Statement stmt = conn.createStatement();
      
             stmt = conn.createStatement();

             rs = stmt.executeQuery("Select * from tbl_blocks where brand_id='" + Brand_id + "'");

            while (rs.next()) {
                buffer = buffer + "<option value='" + rs.getString("id") + "'>" + rs.getString("name") + "</option>";
            }

            buffer = buffer + "</select>";
            stmt.close();
            rs.close();
 
                Statement stmt3 = conn.createStatement();
                ResultSet rs3 = stmt3.executeQuery("Select * From tbl_brand_font_family where brand_id=" + Brand_id + "");

                if (rs3.next()) {
                    Integer font_id1 = rs3.getInt("font_id1");
                    Statement stmt4 = conn.createStatement();
                    ResultSet rs4 = stmt4.executeQuery("Select * From tbl_font_family where id=" + font_id1 + "");
                    if (rs4.next()) {
                        String font_name1 = rs4.getString("font_name");
                        String font_file_name = rs4.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs4.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name1 + "</option>";
                    }
                    rs4.close();
                    stmt4.close();
                    Integer font_id2 = rs3.getInt("font_id2");
                    Statement stmt5 = conn.createStatement();
                    ResultSet rs5 = stmt5.executeQuery("Select * From tbl_font_family where id=" + font_id2 + "");
                    if (rs5.next()) {
                        String font_name2 = rs5.getString("font_name");
                        String font_file_name = rs5.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs5.getString("file_name");
                        
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name2 + "</option>";
                    }
                    rs5.close();
                    stmt5.close();
                    Integer font_id3 = rs3.getInt("font_id3");

                    Statement stmt6 = conn.createStatement();
                    ResultSet rs6 = stmt6.executeQuery("Select * From tbl_font_family where id=" + font_id3 + "");
                    if (rs6.next()) {
                        String font_name3 = rs6.getString("font_name");
                        String font_file_name = rs6.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs6.getString("file_name");
                        
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name3 + "</option>";
                    }
                    rs6.close();
                    stmt6.close();
                    Integer font_id4 = rs3.getInt("font_id4");

                    Statement stmt7 = conn.createStatement();
                    ResultSet rs7 = stmt7.executeQuery("Select * From tbl_font_family where id=" + font_id4 + "");
                    if (rs7.next()) {
                        String font_name4 = rs7.getString("font_name");
                        String font_file_name = rs7.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs7.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name4 + "</option>";
                    }
                    rs7.close();
                    stmt7.close();
                    Integer font_id5 = rs3.getInt("font_id5");

                    Statement stmt8 = conn.createStatement();
                    ResultSet rs8 = stmt8.executeQuery("Select * From tbl_font_family where id=" + font_id5 + "");
                    if (rs8.next()) {
                        String font_name5 = rs8.getString("font_name");
                        String font_file_name = rs8.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs8.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name5 + "</option>";
                    }
                    rs8.close();
                    stmt8.close();
                    Integer font_id6 = rs3.getInt("font_id6");

                    Statement stmt9 = conn.createStatement();
                    ResultSet rs9 = stmt9.executeQuery("Select * From tbl_font_family where id=" + font_id6 + "");
                    if (rs9.next()) {
                        String font_name5 = rs9.getString("font_name");
                        String font_file_name = rs9.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs9.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name5 + "</option>";
                    }
                    rs9.close();
                    stmt9.close();
                    
                    Integer font_id7 = rs3.getInt("font_id7");

                    Statement stmt10 = conn.createStatement();
                    ResultSet rs10 = stmt10.executeQuery("Select * From tbl_font_family where id=" + font_id7 + "");
                    if (rs10.next()) {
                        String font_name5 = rs10.getString("font_name");
                        String font_file_name = rs10.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs10.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name5 + "</option>";
                    }
                    rs10.close();
                    stmt10.close();
                    
                    Integer font_id8 = rs3.getInt("font_id8");

                    Statement stmt11 = conn.createStatement();
                    ResultSet rs11 = stmt11.executeQuery("Select * From tbl_font_family where id=" + font_id8 + "");
                    if (rs11.next()) {
                        String font_name5 = rs11.getString("font_name");
                        String font_file_name = rs11.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs11.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name5 + "</option>";
                    }
                    rs11.close();
                    stmt11.close();                    

                    Integer font_id9 = rs3.getInt("font_id9");

                    Statement stmt12 = conn.createStatement();
                    ResultSet rs12 = stmt12.executeQuery("Select * From tbl_font_family where id=" + font_id9 + "");
                    if (rs12.next()) {
                        String font_name5 = rs12.getString("font_name");
                        String font_file_name = rs12.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs12.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name5 + "</option>";
                    }
                    rs12.close();
                    stmt12.close();                    

                    Integer font_id10 = rs3.getInt("font_id10");

                    Statement stmt13 = conn.createStatement();
                    ResultSet rs13 = stmt13.executeQuery("Select * From tbl_font_family where id=" + font_id10 + "");
                    if (rs13.next()) {
                        String font_name5 = rs13.getString("font_name");
                        String font_file_name = rs13.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs13.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name5 + "</option>";
                    }
                    rs13.close();
                    stmt13.close();                    

                    Integer font_id11 = rs3.getInt("font_id11");

                    Statement stmt14 = conn.createStatement();
                    ResultSet rs14 = stmt14.executeQuery("Select * From tbl_font_family where id=" + font_id11 + "");
                    if (rs14.next()) {
                        String font_name5 = rs14.getString("font_name");
                        String font_file_name = rs14.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs14.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name5 + "</option>";
                    }
                    rs14.close();
                    stmt14.close();                    

                    Integer font_id12 = rs3.getInt("font_id12");

                    Statement stmt15 = conn.createStatement();
                    ResultSet rs15 = stmt15.executeQuery("Select * From tbl_font_family where id=" + font_id12 + "");
                    if (rs15.next()) {
                        String font_name5 = rs15.getString("font_name");
                        String font_file_name = rs15.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs15.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name5 + "</option>";
                    }
                    rs15.close();
                    stmt15.close();                    
                    
                    Integer font_id13 = rs3.getInt("font_id13");

                    Statement stmt16 = conn.createStatement();
                    ResultSet rs16 = stmt16.executeQuery("Select * From tbl_font_family where id=" + font_id13 + "");
                    if (rs16.next()) {
                        String font_name5 = rs16.getString("font_name");
                        String font_file_name = rs16.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs16.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name5 + "</option>";
                    }
                    rs16.close();
                    stmt16.close();                    

                    Integer font_id14 = rs3.getInt("font_id14");

                    Statement stmt17 = conn.createStatement();
                    ResultSet rs17 = stmt17.executeQuery("Select * From tbl_font_family where id=" + font_id14 + "");
                    if (rs17.next()) {
                        String font_name5 = rs17.getString("font_name");
                        String font_file_name = rs17.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs17.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name5 + "</option>";
                    }
                    rs17.close();
                    stmt17.close();

                    Integer font_id15 = rs3.getInt("font_id15");

                    Statement stmt18 = conn.createStatement();
                    ResultSet rs18 = stmt18.executeQuery("Select * From tbl_font_family where id=" + font_id15 + "");
                    if (rs18.next()) {
                        String font_name5 = rs18.getString("font_name");
                        String font_file_name = rs18.getString("font_family_name");
                        font_file_name = font_file_name + "|" + rs18.getString("file_name");
                        buffer1 = buffer1 + "<option value='" + font_file_name + "'>" + font_name5 + "</option>";
                    }
                    rs18.close();
                    stmt18.close();
                   
                    buffer1 = buffer1 + "</select>";

                }

            response.getWriter().println(buffer1+","+buffer);

     
    } catch (Exception e) {
       logger.log(Level.SEVERE, "", e);
    } finally {
        ConnectionManager.getInstance().closeConnection(conn);
    }
%>
