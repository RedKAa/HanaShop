/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.controller;

import hoangnt.model.Cart;
import hoangnt.model.OrderDetails;
import hoangnt.model.Product;
import hoangnt.service.impl.ProductServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class CartControl extends HttpServlet {

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

        String action = request.getParameter("action");
        if (action != null && !action.equals("")) {
            switch (action) {
                case "add to cart":
                    addToCart(request, response);
                    break;
                case "update cart":
                    updateCart(request, response);
                    break;
                case "delete item":
                    deleteItem(request, response);
                    break;
                case "view cart":
                    viewCart(request, response);
                    break;
                default:
                    break;
            }
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

    private void addToCart(HttpServletRequest request, HttpServletResponse response) {
        String productID = request.getParameter("pid");
        ProductServiceImpl productService = new ProductServiceImpl();
        Product item = productService.get(productID);
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.addToCart(productID, 1, item.getPrice());
        session.setAttribute("cart", cart);
        try {
            response.sendRedirect("cart?action=view cart");
        } catch (IOException ex) {
            Logger.getLogger(CartControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) {
        String productID = request.getParameter("pid");
        String updateQuantity = request.getParameter("updateQuantity");
        ProductServiceImpl productService = new ProductServiceImpl();
        Product item = productService.get(productID);
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            if ("increase".equals(updateQuantity)) {
                cart.increaseQuantity(productID, item.getPrice());
            } else if ("decrease".equals(updateQuantity)) {
                cart.decreaseQuantity(productID, item.getPrice());
            }
            session.setAttribute("cart", cart);
            try {
                response.sendRedirect("cart?action=view cart");
            } catch (IOException ex) {
                Logger.getLogger(CartControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
        String productID = request.getParameter("pid");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.removeProduct(productID);
            session.setAttribute("cart", cart);
            try {
                response.sendRedirect("cart?action=view cart");
            } catch (IOException ex) {
                Logger.getLogger(CartControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void viewCart(HttpServletRequest request, HttpServletResponse response) {
        String URL = "view/Cart.jsp";
        ProductServiceImpl productService = new ProductServiceImpl();
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        float total = 0;
        if (cart != null) {
            HashMap<Product, Integer> displayCart = new HashMap<>();
            for (Map.Entry<String, OrderDetails> entry : cart.getCart().entrySet()) {
                String key = entry.getKey();
                Product p = productService.get(key);
                OrderDetails value = entry.getValue();
                displayCart.put(p, value.getQuantity());
                total += value.getPrice();
            }
            request.setAttribute("cart", displayCart);
            request.setAttribute("total", total);
        }
        try {
            request.getRequestDispatcher(URL).forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(CartControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CartControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
