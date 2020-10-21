/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.controller;

import at.kaindorf.beans.OrderSummaryItem;
import at.kaindorf.beans.Pizza;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oujia
 */
@WebServlet(name = "PizzaOrderServlet", urlPatterns = {"/PizzaOrderServlet"})
public class PizzaOrderServlet extends HttpServlet {

    private List<Pizza> liPizzas = new ArrayList<>();

    public void input_pizza_data(Cookie cookie) {
        String pfad = "";
        if (cookie == null) {
            pfad = this.getServletContext().getRealPath("/pizzatabelle_ger.csv");
        } else if (cookie.getValue().equals("English")) {
            pfad = this.getServletContext().getRealPath("/pizzatabelle_eng.csv");
        } else {
            pfad = this.getServletContext().getRealPath("/pizzatabelle_ger.csv");
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pfad), "UTF-8"));
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");
                //String name, String beschreibung, URI link, int preis
                //Name,Beschreibung,Bild,Preis
                liPizzas.add(new Pizza(tokens[0], tokens[1], URI.create(tokens[2]), Float.parseFloat(tokens[3])));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PizzaOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PizzaOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        input_pizza_data(null);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("PizzaOrder.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void languageSelect(HttpServletRequest request, HttpServletResponse response) {
        Cookie langCookie = new Cookie("language", request.getParameter("Language"));
        langCookie.setMaxAge(-1);
        response.addCookie(langCookie);
        request.setAttribute("pizza-information-list", liPizzas);
        request.setAttribute("lang-cookie", langCookie);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(liPizzas);
        languageSelect(request, response);
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        languageSelect(request, response);
        float gesamtpreis = 0;
        List<OrderSummaryItem> liItems = new LinkedList<>();
        for (Pizza liPizza : liPizzas) {
            gesamtpreis += liPizza.getPreis() * Integer.parseInt(request.getParameter(liPizza.getName()));
            liItems.add(new OrderSummaryItem(liPizza.getPreis(), liPizza.getName(), Integer.parseInt(request.getParameter(liPizza.getName()))));
        }
        request.setAttribute("gesamtpreis", gesamtpreis);
        request.setAttribute("items", liItems);
        request.setAttribute("lieferadresse", request.getParameter("Lieferadresse"));
        request.getRequestDispatcher("PizzaOrderSummary.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
