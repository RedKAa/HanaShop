/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.controller;

import hoangnt.utilities.ResourceManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class ServletDispatcher extends HttpServlet {

    private final String HOME_CONTROL = "home";

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = HOME_CONTROL;
        try {
            String button = request.getParameter("btAction");
            if (button == null || "".equals(button.trim())) {
            } else if ("home".equals(button)) {
                url="home";
            } else if ("login".equals(button)) {
                url = "login";
            } else if ("logout".equals(button)) {
                url = "logout";
            } else if ("searchByName".equals(button)) {
                url = "searchByName";
            } else if ("searchByPrice".equals(button)) {
                url = "searchByPrice";
            } else if ("searchByCategory".equals(button)) {
                url = "category";
            } else if ("manager".equals(button)) {
                url = "/manager";
            } else if ("addProductByAdmin".equals(button)) {
                url = "/addProductByAdmin";
            } else if ("editProduct".equals(button)) {
                url = "/editProduct";
            } else if ("loadEdit".equals(button)) {
                url = "/loadEdit";
            } else if ("deleteProduct".equals(button)) {
                url = "/deleteProduct";
            } else if ("deleteProducts".equals(button)) {
                url = "/deleteProducts";
            } else if ("searchByNameAdmin".equals(button)) {
                url = "/searchByNameAdmin";
            } else if ("viewCart".equals(button)) {
                url = "/viewCart";
            } else if ("viewProduct".equals(button)) {
                url = "/viewProduct";
            } else {
                url = "view/Invalid.html";
            }

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        processRequest(request, response);
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
