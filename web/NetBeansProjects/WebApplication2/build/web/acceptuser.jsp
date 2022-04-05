
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%!
 Connection con;
 PreparedStatement ps1, ps2;
 public void jspInit()
 {
 try
 {
  Class.forName("com.mysql.cj.jdbc.Driver");
 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iplab","root","1234");
 ps1 = con.prepareStatement("select count(*) from USERS where username = ? and password=?");
 ps2 = con.prepareStatement("select * from USERS");
 }
 catch(Exception ex)
 {
 ex.printStackTrace();
}
 }
%>
<%
 String param = request.getParameter("s1");
 if(param =="link")
 {
 ResultSet rs = ps2.executeQuery();
 out.println("<table>");
 while(rs.next())
 {
 out.println("<tr>");
 out.println("<td>"+rs.getString(1)+"</td>");
 out.println("<td>"+rs.getString(2)+"</td");
 out.println("</tr>");
 }
 out.println("</table>");
 rs.close();
 }
 else
 {
 String user = request.getParameter("uname");
 String pass = request.getParameter("pass");
 ps1.setString(1,user);
 ps1.setString(2,pass);
 ResultSet rs = ps1.executeQuery();
 int cnt = 0;
 if (rs.next())
	 cnt = rs.getInt(1); // index 1 is the " ???" column
 if(cnt == 0)
 	out.println("<b><i><font color=red>Invalid credential</fonr></i></b>");
 else
 {
 out.println("<form><center><fieldset style= width:55% height:60%; >");
 out.println("<b><font color=red>Valid Credential..</fonr></b><br>");
 out.println("<b><a href=examclient.html><font size=6 color=blue>Click Here to take Online   Exam</font></a></b>");

 out.println("</fieldset></form>");
 }
 }
%>
<%!
 public void jspDestroy()
 {
 try
 {
 ps1.close();
 ps2.close();
 con.close();
 }
 catch(Exception ex)
 {
 ex.printStackTrace();
 }
 }
%>
