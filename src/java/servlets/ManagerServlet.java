/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Consumer;
import entity.Product;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ConsumerFacade;
import session.ProductFacade;

/**
 *
 * @author A
 */
@WebServlet(name = "ManagerServlet", urlPatterns = {
    "/addProduct",
    "/createProduct",
    "/listConsumers",
})
public class ManagerServlet extends HttpServlet {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private ConsumerFacade consumerFacade;
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
        HttpSession session = request.getSession(false);
        if (session == null) {
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response);
            request.setAttribute("info", "Войдите в систему");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user==null) {
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response);
            request.setAttribute("info", "Войдите в систему");
            return;
        }
        
        if (!"manager".equals(user.getLogin())) {
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response);
            request.setAttribute("info", "Войдите в систему");
            return;
        }
        String path = request.getServletPath();
        switch(path){
            case "/addProduct":
                    request.getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
                    break;
            case "/createProduct":
                String name = request.getParameter("name");
                String price = request.getParameter("price");
                String quantity = request.getParameter("quantity");
                String videocard = request.getParameter("videocard");
                String ram = request.getParameter("ram");
                String cpu = request.getParameter("cpu");
                if("".equals(name) || name == null 
                        || "".equals(videocard) || videocard == null
                        || "".equals(ram) || ram == null
                        || "".equals(price) || price == null
                        || "".equals(cpu) || cpu == null
                        || "".equals(quantity) || quantity == null){
                    request.setAttribute("info","Заполните все поля формы");
                    request.setAttribute("name",name);
                    request.setAttribute("price",price);
                    request.setAttribute("quantity",quantity);
                    request.setAttribute("videocard",videocard);
                    request.setAttribute("ram",ram);
                    request.setAttribute("cpu",cpu);
                    request.getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
                    break; 
                }
                Product product = new Product(name, Integer.parseInt(price), Integer.parseInt(quantity), videocard, ram, cpu);
                productFacade.create(product);
                request.setAttribute("info","Добавлен товар: " +product.toString() );
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
                
            case "/listConsumers":
                List<Consumer> listConsumers = consumerFacade.findAll();
                request.setAttribute("listConsumers", listConsumers);
                request.getRequestDispatcher("/WEB-INF/listConsumers.jsp").forward(request, response);
                break;    
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
