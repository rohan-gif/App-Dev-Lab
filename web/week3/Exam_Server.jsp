<%-- 
    Document   : Exam_Server
    Created on : 16-Feb-2022, 8:19:26 pm
    Author     : Shivam
--%>

<%@page contentType="text/html" language="java" import="java.sql.*"%>
<html>
<head>
<title>Online Exam Server</title>

</head>
<body>
<h2 style="text-align:center">ONLINE EXAMINATION</h2>

<hr/>
<%
String name=request.getParameter("name");
String roll=request.getParameter("roll");
String str1=request.getParameter("ans1");
String str2=request.getParameter("ans2");
String str3=request.getParameter("ans3");
//out.println(name);
//out.println(roll);
//out.println("<br><br>");
int mark=0;
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3310/lab3","root","admin");

Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("SELECT * FROM ans");

int i=1;
while(rs.next())
{
//out.println("hello i am here");
 if(i==1)
 {
  String dbans1=rs.getString(2);
  //out.println(dbans1);

  if(str1.equals(dbans1))
  {
   mark=mark+1;
  }
 }
 if(i==2)
 {
  String dbans2=rs.getString(2);
    //out.println(dbans2);

  if(str2.equals(dbans2))
  {
   mark=mark+1;
  }
 }
 if(i==3)
 {
  String dbans3=rs.getString(2);
    //out.println(dbans3);

  if(str3.equals(dbans3))
  {
   mark=mark+1;
  }
 }
 i++;
}
out.println("<br><br>");
out.println("Name : "+name);
out.println("<br><br>");
out.println("Roll No :"+roll);
//out.println("<br><br>");
out.println("<h4>Your Mark Is : "+mark+"</h4>");


String q="insert into result(name,roll,marks) values(?,?,?)";
PreparedStatement pstmt=con.prepareStatement(q);
pstmt.setString(1,name);
pstmt.setString(2,roll);
pstmt.setInt(3,mark);
pstmt.executeUpdate();

%>
<p>
<a href="Exam_Client.html">Back To Main Page</a>
</p>
</form>
</body>
</html>
