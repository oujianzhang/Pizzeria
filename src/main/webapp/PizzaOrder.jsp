<%-- 
    Document   : PizzaOrderGer
    Created on : Oct 7, 2020, 11:23:59 AM
    Author     : oujia
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="at.kaindorf.beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Page</title>
        <link href="Pizzeria.css" rel="stylesheet" type="text/css">
        <script src="PizzaOrder.js"></script> 
    </head>

    <body>
        <div>
            <h1>Pizzeria</h1>
            <select name="Language">
                <option>Deutsch</option>
                <option>English</option>
            </select>
        </div>
        <%
            List<Pizza> pizzen = (ArrayList) request.getAttribute("pizza-information-list");
        %>

        <form action="PizzaOrderServlet" method="POST">
            <div class="Item">
                <table border="0">
                    <tbody>
                        
                            <%
                                for (int i = 0; i < 5; i++) {
                                    out.println("<tr>");
                                    out.println("<td><img src='" + pizzen.get(i).getLink() + "' alt='Margaritha'></td>");
                                    out.println("<td>" + pizzen.get(i).getName() + "</td>");
                                    out.println("<td>" + pizzen.get(i).getPreis() + " €</td>");
                                    out.println("<td>" + pizzen.get(i).getBeschreibung() + "</td>");
                                    out.println("<td><input type='number' id='Anzahl' name='"+ pizzen.get(i).getName() + "' min='0' max='100' value='0'></td>");
                                    out.println("</tr>");
                                }
                            %>
                        
                    </tbody>
                </table>
            </div>

            <div class="Bestellung">
                <label>Lieferadresse:<br></label>
                <input type="text" name="Lieferadresse" value="Lieferadresse"/>
                <input type="submit" value="Bestellen" name="bestellen"/>
            </div>
        </form>
    </body>
</html>