
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


public class signup extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response,String message)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            if(message.equals("success")){
                out.println("<title>success</title>");  
            }
            else{
            out.println("<title>error</title>");  
            }
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>COURSE REGISTRATION APP </h1>");
            
            if(message.equals("error2")){
            out.println("<h4>password didn't matched </h4>");
            out.println("<a href=\"/lab/signup\"><input type=\"button\" value=\"try again\"></a>");
            }
            else{
                if(message.equals("error1")){
                    out.println("<h4>Username already exists try login </h4>");
                }
                else{
                 out.println("<h4> Successfully Registered, Please Login Now </h4>");
                }
                out.println("<a href=\"/lab/login\"><input type=\"button\" value=\"Login\"></a>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("signup.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name=request.getParameter("username");
        String password = request.getParameter("password");
        String confpassword=request.getParameter("confpassword");
        
       if(password.equals(confpassword)){
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydv","root","1234");
                    String q="insert into user(name,password) values(?,?)";
                    PreparedStatement pstmt=con.prepareStatement(q);
                    pstmt.setString(1,name);
                    pstmt.setString(2,password);
                    pstmt.executeUpdate();
                    processRequest(request,response,"success");
                }catch(Exception e){
                    processRequest(request,response,"error1");
                }
            }
       else{
           processRequest(request,response,"error2");
       }
        }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
