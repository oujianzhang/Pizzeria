<%-- 
    Document   : PizzaOrderSummary
    Created on : Oct 13, 2020, 9:18:26 AM
    Author     : oujia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="at.kaindorf.beans.OrderSummaryItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="PizzaOrderServlet" method="POST">
            <div>
                <h1>Pizzeria</h1>
                <select name="Language">
                    <option>Deutsch</option>
                    <option>English</option>
                </select>
            </div>

            <div>
                <table>
                    <thead>
                        <tr>
                            <th>Pizza</th>
                            <th>Pr.</th>
                            <th>Stk.</th>
                            <th>Ges.</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- 
            request.setAttribute("gesamtpreis", gesamtpreis);
            request.setAttribute("items", liItems);
            request.setAttribute("lieferadresse", request.getParameter("Lieferadresse")); -->


                        <c:forEach var="item" items="${items}">
                            <c:if test = "${item.getStueckanzahl() > 0}">
                                <tr>
                                    <td>
                                        ${item.getBezeichnung()}
                                    </td>
                                    <td>
                                        ${item.getPreis()} €
                                    </td>
                                    <td>
                                        ${item.getStueckanzahl()}
                                    </td>
                                    <td>
                                        ${String.format("%.2f €", item.getPreis() * item.getStueckanzahl())}
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>

                <c:out value="Gesamtpreis: ${gesamtpreis} €"/>
                <br>
                <c:out value="Lieferadresse: ${lieferadresse}"/>
                <br>
            </div>
        </form>
        <button onclick="window.location.href = 'http://localhost:8080/Pizzeria/';">
            Zurück
        </button>
    </body>
</html>
