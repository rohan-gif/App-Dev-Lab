/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author the AK
 */
public class login extends HttpServlet {

   
    protected void errorRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>error</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>COURSE REGISTRATION APP </h1>");
            out.println("<h4>username or passord is incorrect</h4>");
            out.println("<a href=\"/lab/login\"><input type=\"button\" value=\"try again\"></a>");            
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name=request.getParameter("username");
        String password = request.getParameter("password");
        
         try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydv","root","1234");
                    String q="select * from user where name='"+name+"' and password='"+password+"'";
                    Statement stmt=con.createStatement();
                    ResultSet rs=  stmt.executeQuery(q);
                      if(!rs.next()){
                          throw new Exception();
                      }
                    String url="/lab/courses?params=";
                    url+=name;
                    response.sendRedirect(url);
                }catch(Exception e){
                    errorRequest(request,response);
                }
        //check user if in database
        
        //if not 
        //render 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
