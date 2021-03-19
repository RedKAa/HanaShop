/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.controller.admin;

import hoangnt.model.ErrorMessage;
import hoangnt.model.Product;
import hoangnt.model.User;
import hoangnt.service.impl.ProductServiceImpl;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class AddProductByAdminControl extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String URL = "ServletDispatcher?btAction=manager";
        ErrorMessage msg = new ErrorMessage();
        User user = (User) session.getAttribute("user");

        //check user role 
        boolean isAdmin = false;
        if (user != null && user.checkAdmin()) {
            isAdmin = true;
        }//checked

        if (isAdmin) {
            String productID = UUID.randomUUID().toString().substring(0, 10);
            String name = request.getParameter("name");
            String image = request.getParameter("image");
            String price = request.getParameter("price");

            String quantity = request.getParameter("quantity");
            String description = request.getParameter("description");
            String cateID = request.getParameter("category");
            boolean status = request.getParameter("status") != null;
            Product dto;

            try {
                int quantityInt = Integer.parseInt(quantity);
                float priceFloat = Float.parseFloat(price);
                if (priceFloat <= 0 || priceFloat > Float.MAX_VALUE) {
                    throw new Exception("Price must be a positive number!");
                }
                if (quantityInt <= 0 || quantityInt > Integer.MAX_VALUE) {
                    throw new Exception("Quantity must be a positive number!");
                }
                dto = new Product(productID, name, image, description, priceFloat, quantityInt, status, cateID);
                ProductServiceImpl productService = new ProductServiceImpl();
                if (!productService.insert(dto, user.getId())) {
                    msg.addMessage("Added failed");
                } else {
                    msg.addMessage(name + " has been added!");
                }
            } catch (Exception e) {
                msg.addMessage(e.getMessage());
                log(e.getMessage());
            } finally {
                request.setAttribute("msg", msg);
                request.setAttribute("action", "manager");
                RequestDispatcher dispatcher = request.getRequestDispatcher(URL);
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("view/Invalid.html");
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
