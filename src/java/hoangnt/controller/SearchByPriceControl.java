/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.controller;

import hoangnt.model.ErrorMessage;
import hoangnt.model.Product;
import hoangnt.model.User;
import hoangnt.service.impl.ProductServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class SearchByPriceControl extends HttpServlet {

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
        ErrorMessage errMsg = new ErrorMessage();
        PrintWriter out = response.getWriter();
        String URL = "view/Home.jsp";
        String min = request.getParameter("minPrice");
        String max = request.getParameter("maxPrice");
        long minPrice = 0;
        long maxPrice = 0;
        if (!"".equals(min.trim()) && !"".equals(max.trim())) {
            try {
                minPrice = Integer.parseInt(min);
                maxPrice = Integer.parseInt(max);
                if (minPrice > maxPrice || minPrice < 10000 || maxPrice < 10000 || maxPrice > Float.MAX_VALUE) {
                    throw new Exception("");
                }
            } catch (Exception e) {
                errMsg.addMessage("Invalid range of price, (10000-"+Float.MAX_VALUE+")!");
            }
        }

        //caculate begin_row and end_row of query_result want to take
        int beginIndex, endIndex;
        int pageIndex = 1; //default value
        int pageSize = 15;

        String page = request.getParameter("pageIndex");
        if (page != null) {
            pageIndex = Integer.parseInt(page);
        }
        beginIndex = pageSize * (pageIndex - 1) + 1;
        endIndex = pageSize * pageIndex;
        //caculated

        try {
            //check user role 
            boolean isAdmin = false;
            HttpSession session = request.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("user");
                if (user != null && user.checkAdmin()) {
                    isAdmin = true;
                }
            }//checked

            //Get list product - search by name
            ProductServiceImpl productService = new ProductServiceImpl();
            List<Product> products = productService.seachByPrice(minPrice, maxPrice, beginIndex, endIndex, isAdmin);

            //Paging
            int countResult = productService.countSearchByPrice(minPrice, maxPrice, isAdmin);
            int endPage = countResult / pageSize;
            if (countResult % pageSize != 0) {
                endPage++;
            }
            //end of paging
            //load bestSeller
            Product top = productService.getBestSellerProduct();
            request.setAttribute("top", top);
            //end of load bestSeller

            request.setAttribute("ERROR", errMsg.getMsg());
            request.setAttribute("pageIndex", pageIndex);
            request.setAttribute("endPage", endPage);
            request.setAttribute("Products", products);
            request.setAttribute("action", "searchByPrice");
            request.setAttribute("minPrice", minPrice);
            request.setAttribute("maxPrice", maxPrice);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(URL);
            rd.forward(request, response);
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
