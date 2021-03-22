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
import session.UserFacade;

/**
 *
 * @author A
 */
@WebServlet(name = "UserServlet", urlPatterns = {
    
    
    "/buyProduct",
    "/addMoneyToConsumer",
    "/addMoney"
        })
public class UserServlet extends HttpServlet {
    @EJB 
    private ProductFacade productFacade;
    @EJB 
    private ConsumerFacade consumerFacade;
    @EJB
    private UserFacade userfacade;
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
        String path = request.getServletPath();
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
        switch (path) {
            
                
            
            
            case "/buyProduct":
                String productId = request.getParameter("productId");                
                user = (User) session.getAttribute("user");
                
                Product product = productFacade.find(Long.parseLong(productId));
                Consumer consumer = consumerFacade.find(user.getId());
                
                if (consumer.getCash()<product.getPrice()){
                    request.setAttribute("info","Недостаточно средств");
                    
                    request.getRequestDispatcher("/main").forward(request, response);
                    break;
                }
                if (product.getQuantity()<=0){
                    request.setAttribute("info","Нету товара на складе");
                    
                    request.getRequestDispatcher("/main").forward(request, response);
                    break;
                }
                consumer.setCash(consumer.getCash()-(product.getPrice()));
                consumerFacade.edit(consumer);
                product.setQuantity(product.getQuantity()-1);
                productFacade.edit(product);
                session.setAttribute("cash", consumer.getCash());
                request.setAttribute("info","Товар куплен");
                request.getRequestDispatcher("/main").forward(request, response);
                break;
            case "/addMoneyToConsumer":
//                listConsumers = consumerFacade.findAll();
//                request.setAttribute("listConsumers", listConsumers);
                User userData = (User) session.getAttribute("user");
                int cash = (int) session.getAttribute("cash");
                String role = (String) session.getAttribute("role");
                request.setAttribute("name", userData.getUser().getFirstName() +" "+ userData.getUser().getLastName());
                request.setAttribute("cash",cash); 
                request.setAttribute("id", userData.getUser().getId()); 
                request.setAttribute("role", role);
                
                
                request.getRequestDispatcher("/WEB-INF/addMoneyToConsumer.jsp").forward(request, response);
                break;
            case "/addMoney":
                  String money = request.getParameter("money");
                  String consumerId = request.getParameter("id");
                  if(consumerId == null || "".equals(consumerId) || money == null || "".equals(money)){
                    request.setAttribute("info", "Заполните все поля");
                    userData = (User) session.getAttribute("user");
                    cash = (int) session.getAttribute("cash");
                    request.setAttribute("name", userData.getUser().getFirstName() +" "+ userData.getUser().getLastName());
                    request.setAttribute("cash",cash); 
                    request.setAttribute("id", userData.getUser().getId()); 
                    request.getRequestDispatcher("/WEB-INF/addMoneyToConsumer.jsp").forward(request, response);
                    break;
                  }
                consumer = consumerFacade.find(Long.parseLong(consumerId));
                
                consumer.setCash(consumer.getCash()+Integer.parseInt(money));
                consumerFacade.edit(consumer);
                session.setAttribute("cash", consumer.getCash());
                request.setAttribute("info", "Пополнен кошелек");
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//                break;
                  
                  
                  request.getRequestDispatcher("/main").forward(request, response);
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
