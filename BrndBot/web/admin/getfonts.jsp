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
                        buffer1 = buffer1 + "<option value='" + font_name1 + "'>" + font_name1 + "</option>";
                    }
                    rs4.close();
                    stmt4.close();
                    Integer font_id2 = rs3.getInt("font_id2");
                    Statement stmt5 = conn.createStatement();
                    ResultSet rs5 = stmt5.executeQuery("Select * From tbl_font_family where id=" + font_id2 + "");
                    if (rs5.next()) {
                        String font_name2 = rs5.getString("font_name");
                        buffer1 = buffer1 + "<option value='" + font_name2 + "'>" + font_name2 + "</option>";
                    }
                    rs4.close();
                    stmt4.close();
                    Integer font_id3 = rs3.getInt("font_id3");

                    Statement stmt6 = conn.createStatement();
                    ResultSet rs6 = stmt6.executeQuery("Select * From tbl_font_family where id=" + font_id3 + "");
                    if (rs6.next()) {
                        String font_name3 = rs6.getString("font_name");
                        buffer1 = buffer1 + "<option value='" + font_name3 + "'>" + font_name3 + "</option>";
                    }
                    rs4.close();
                    stmt4.close();
                    Integer font_id4 = rs3.getInt("font_id4");

                    Statement stmt7 = conn.createStatement();
                    ResultSet rs7 = stmt7.executeQuery("Select * From tbl_font_family where id=" + font_id4 + "");
                    if (rs7.next()) {
                        String font_name4 = rs7.getString("font_name");
                        buffer1 = buffer1 + "<option value='" + font_name4 + "'>" + font_name4 + "</option>";
                    }
                    rs4.close();
                    stmt4.close();
                    Integer font_id5 = rs3.getInt("font_id5");

                    Statement stmt8 = conn.createStatement();
                    ResultSet rs8 = stmt8.executeQuery("Select * From tbl_font_family where id=" + font_id5 + "");
                    if (rs8.next()) {
                        String font_name5 = rs8.getString("font_name");
                        buffer1 = buffer1 + "<option value='" + font_name5 + "'>" + font_name5 + "</option>";
                    }
                    rs4.close();
                    stmt4.close();
                    buffer1 = buffer1 + "</select>";

                    /*-------------------------------script to get the font sizes from the database ----------------------------*/
                    Integer font_size_id1 = rs3.getInt("font_size1");
                    Statement stmt9 = conn.createStatement();

                    ResultSet rs9 = stmt9.executeQuery("Select * From tbl_font_size where id=" + font_size_id1 + "");
                    if (rs9.next()) {
                        String font_size1 = rs9.getString("font_size");
                        font_size1 = font_size1 + "px";
                        buffer2 = buffer2 + "<option value=" + font_size1 + ">" + font_size1 + "</option>";
                    }
                    rs4.close();
                    stmt4.close();
                    Integer font_size_id2 = rs3.getInt("font_size2");
                    Statement stmt10 = conn.createStatement();
                    ResultSet rs10 = stmt10.executeQuery("Select * From tbl_font_size where id=" + font_size_id2 + "");
                    if (rs10.next()) {
                        String font_size2 = rs10.getString("font_size");
                        font_size2 = font_size2 + "px";
                        buffer2 = buffer2 + "<option value=" + font_size2 + ">" + font_size2 + "</option>";
                    }
                    rs4.close();
                    stmt4.close();
                    Integer font_size_id3 = rs3.getInt("font_size3");
                    Statement stmt11 = conn.createStatement();
                    ResultSet rs11 = stmt11.executeQuery("Select * From tbl_font_size where id=" + font_size_id3 + "");
                    if (rs11.next()) {
                        String font_size3 = rs11.getString("font_size");
                        font_size3 = font_size3 + "px";
                        buffer2 = buffer2 + "<option value=" + font_size3 + ">" + font_size3 + "</option>";
                    }
                    rs4.close();
                    stmt4.close();
                    Integer font_size_id4 = rs3.getInt("font_size4");
                    Statement stmt12 = conn.createStatement();
                    ResultSet rs12 = stmt12.executeQuery("Select * From tbl_font_size where id=" + font_size_id4 + "");
                    if (rs12.next()) {
                        String font_size4 = rs12.getString("font_size");
                        font_size4 = font_size4 + "px";
                        buffer2 = buffer2 + "<option value=" + font_size4 + ">" + font_size4 + "</option>";

                    }
                    rs4.close();
                    stmt4.close();
                    Integer font_size_id5 = rs3.getInt("font_size5");
                    Statement stmt13 = conn.createStatement();
                    ResultSet rs13 = stmt13.executeQuery("Select * From tbl_font_size where id=" + font_size_id5 + "");
                    if (rs13.next()) {
                        String font_size5 = rs13.getString("font_size");
                        font_size5 = font_size5 + "px";
                        buffer2 = buffer2 + "<option value=" + font_size5 + ">" + font_size5 + "</option>";
                    }
                    rs4.close();
                    stmt4.close();
                    buffer2 = buffer2 + "</select>";

                }
            

            response.getWriter().println(buffer1+","+buffer);

     
    } catch (Exception e) {
       logger.log(Level.SEVERE, "", e);
    } finally {
        ConnectionManager.getInstance().closeConnection(conn);
    }
%>
