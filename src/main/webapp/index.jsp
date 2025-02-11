<%-- 
    Document   : index
    Created on : Jan. 27, 2025, 11:10:14 p.m.
    Author     : Artist-Code
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form to use JSP</title>
    </head>
    <body>
        <h1>Data of the user</h1>
        <form action="SvUsers" method="POST">
            <p>
                <label>Name: </label>
                <input type="text" name="name">
            </p>
            <p>
                <label>Last Name: </label>
                <input type="text" name="lastName">
            </p>
            <p>
                <label>Phone: </label>
                <input type="text" name="phone">
            </p>
            <button type="submit">Submit</button>
        </form>

        <h1>List of the users</h1>
        <p>Click on the following button to show the list of the user</p>
        <form action="SvUsers" method="GET">
            <button type="submit">Show users </button>
        </form>
    </body>
</html>
