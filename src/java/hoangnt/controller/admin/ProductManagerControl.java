/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.controller.admin;

import hoangnt.model.Category;
import hoangnt.model.Product;
import hoangnt.model.User;
import hoangnt.service.impl.CategoryServiceImpl;
import hoangnt.service.impl.ProductServiceImpl;
import java.io.IOException;
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
public class ProductManagerControl extends HttpServlet {

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
        String URL = "view/admin/ManagerProduct.jsp";

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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //check user role 
        boolean isAdmin = false;
        if (user != null && user.checkAdmin()) {
            isAdmin = true;
        }//checked
//            
        if (isAdmin) {
            try {
                //load list of products
                ProductServiceImpl productService = new ProductServiceImpl();
                List<Product> products = productService.getAll(beginIndex, endIndex, true);
                //end of load products

                //Paging
                int countResult = productService.countAll(true);
                int endPage = countResult / pageSize;
                if (countResult % pageSize != 0) {
                    endPage++;
                }
                //end of paging

                //load list of category
                CategoryServiceImpl categoryService = new CategoryServiceImpl();
                List<Category> category = categoryService.getAll();
                request.setAttribute("Category", category);
                //end of load category

                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("endPage", endPage);
                request.setAttribute("Products", products);
                request.setAttribute("action", "manager");
            } finally {
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
