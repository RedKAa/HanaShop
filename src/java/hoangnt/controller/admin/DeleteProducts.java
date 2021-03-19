/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.controller.admin;

import hoangnt.model.User;
import hoangnt.service.impl.ProductServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
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
public class DeleteProducts extends HttpServlet {

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
        String URL = "manager";
        String msg = "Deleted!";

        String productList[] = request.getParameterValues("deleteList");
        HttpSession session = request.getSession();
        User userUpdate = (User) session.getAttribute("user");
        //check user role 
        boolean isAdmin = false;
        if (userUpdate != null && userUpdate.checkAdmin()) {
            isAdmin = true;
        }//checked

        if (isAdmin && productList.length >= 1) {
            int count = 0;
            boolean error = false;
            for (String productID : productList) {
                if (!productID.isEmpty() && productID != null) {
                    ProductServiceImpl productService = new ProductServiceImpl();
                    if (!productService.delete(productID, userUpdate.getId())) {
                        error = true;
                        msg += productService.get(productID).getName()+", ";
                    }else{
                        count++;
                    }
                }
            }
            if(error){
                msg += "deleted failed!\n";
            }
            if(count >= 1){
                msg+= count+" products has been deleted!";
            }
            request.setAttribute("msg", msg);
            request.setAttribute("action", "manager");
            RequestDispatcher dispatcher = request.getRequestDispatcher(URL);
            dispatcher.forward(request, response);
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
