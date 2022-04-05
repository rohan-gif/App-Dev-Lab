
package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

public class course extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username=request.getParameter("params");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>course</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>COURSE REGISTRATION</h1>");

            out.println("<form action=\"course\" method=\"post\">\n" +
"        <h4>WelCome "+ username+" </h4>\n" +
"\n" +
"        <label for=\"roll\">Roll Number: </label>\n" +
"        <input type=\"text\" name=\"roll\">\n" +
"        <br>\n" +
"        <label for=\"year\">Year : </label>\n" +
"        <input type=\"text\" name=\"year\">\n" +
"        <br>\n" +
"        <label for=\"semester\">semester : </label>\n" +
"        <input type=\"text\" name=\"semester\">\n" +
"        <br>\n" +
"        <label for=\"course\">Course : </label>\n" +
"        <input type=\"text\" name=\"course\">\n" +
"        <br><br>\n" +
"        <input type=\"submit\" value=\"Submit\">\n" +
"        <a href=\"/prjj/\"><input type=\"button\" value=\"Exit\"></a>\n"+"<br><br>" +
"        <input type=\"text\" name=\"params\" value="+username + " hidden>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("params");
        String roll=request.getParameter("roll");
        String year=request.getParameter("year");
        String sem=request.getParameter("semester");
        String course=request.getParameter("course");
        try ( PrintWriter out = response.getWriter()) {
        if(username==null && username.equals("")){
            out.println("INVALID USER");
        }
        else{
            try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/lab2","root","123456");
                    String q="insert into courses(name,roll,year,sem,course) values(?,?,?,?,?)";
                    PreparedStatement pstmt=con.prepareStatement(q);
                    pstmt.setString(1,username);
                    pstmt.setString(2,roll);
                    pstmt.setString(3,year);
                    pstmt.setString(4,sem);
                    pstmt.setString(5,course);
                    pstmt.executeUpdate();
                    out.println("COURSE REGISTRATION SUCCESSFULL");
                }catch(Exception e){
                    out.println("COURSE REGISTRATION FAILED");

                }
            
        }
                
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
