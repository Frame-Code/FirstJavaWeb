<%-- 
    Document   : showUsers
    Created on : Jan. 29, 2025, 5:11:13 p.m.
    Author     : Artist-Code
--%>

<%@page import="com.mycompany.firstweb.dto.UserDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show users</title>
    </head>
    <body>
        <h1>Show users</h1>
        <!.-<!--One way to do this-->

        <%
            /*
            List<User> users = (List) request.getSession().getAttribute("ListUsers");
            int cont = 1;
            String htmlList = "";
            for (User user : users) {
                htmlList += "<p><b>User N°" + cont + "</b></p>"
                        + "<p>ID: " + user.getName() + "</p>";
                cont++;
            }
            
            *Then of the &> put the target <=htmlList> adding the %% corresponding
             */
        %>



        <!<!--Second way to do this -->
        <%
            List<UserDTO> users = (List) request.getSession().getAttribute("ListUsers");
            int cont = 1;
            for (UserDTO user : users) {
        %>
            <p><b>User N°<%=cont%></b></p>
            <p>Name: <%=user.getName()%> </p>
            <p>Last Name: <%=user.getLastName()%> </p>
            <p>Phone: <%=user.getPhone()%> </p>
            <p>-------------------------<br></p>
        <%
            cont++;
            }%>

    </body>
</html>
