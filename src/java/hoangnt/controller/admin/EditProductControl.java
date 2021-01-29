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
public class EditProductControl extends HttpServlet {

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
        String URL = "view/admin/EditProduct.jsp";
        ErrorMessage msg = new ErrorMessage();

        User userUpdate = (User) session.getAttribute("user");

        String productID = request.getParameter("ID");
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String description = request.getParameter("description");
        String cateID = request.getParameter("category");
        boolean status = request.getParameter("status") != null;

        Product dto = null;

        try {
            int quantityInt = Integer.parseInt(quantity);
            float priceFloat = Float.parseFloat(price);
            if (quantityInt <= 0 || quantityInt > Integer.MAX_VALUE) {
                throw new Exception("Quantity must be a positive number!");
            }
            if (priceFloat <= 0 || priceFloat > Float.MAX_VALUE) {
                throw new Exception("Price must be a positive number!");
            }
            dto = new Product();
            dto.setId(productID);
            dto.setName(name);
            dto.setImage(image);
            dto.setPrice(priceFloat);
            dto.setQuantity(quantityInt);
            dto.setDescription(description);
            dto.setCategoryID(cateID);
            dto.setStatus(status);
    
            ProductServiceImpl productService = new ProductServiceImpl();

            if (!productService.edit(dto, userUpdate.getId())) {
                msg.addMessage("Edit failed!");
            }
            msg.addMessage("Updated!");
        } catch (Exception e) {
            URL = "ServletDispatcher?btAction=loadEdit&pid="+productID;
            msg.addMessage(e.getMessage());
            log(e.getMessage());
        } finally {
            request.setAttribute("product", dto);
            request.setAttribute("msg", msg);
            request.setAttribute("action", "manager");
            RequestDispatcher dispatcher = request.getRequestDispatcher(URL);
            dispatcher.forward(request, response);
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
