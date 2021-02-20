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
@WebServlet(name = "LoginServlet", urlPatterns = {
    "/showLoginForm",
    "/login",
    "/addConsumer",
    "/createConsumer",
    "/listProducts",
    "/logout"
})
public class LoginServlet extends HttpServlet {
    @EJB
    UserFacade userFacade;
    @EJB 
    private ConsumerFacade consumerFacade;
    @EJB 
    private ProductFacade productFacade;
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
        
        switch(path){
            case "/showLoginForm":
                request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response);
                break;
                
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User user = userFacade.findByLogin(login);
                
                if (user==null) {
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/showLoginForm").forward(request, response);
                    break;
                }
                
                if (!password.equals(user.getPassword())) {
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/showLoginForm").forward(request, response);
                    break;
                }
                
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);                            
                request.setAttribute("info", "Вы вошли!");
                request.getRequestDispatcher("index.jsp").forward(request, response);;
                break;
                
            case "/logout":
                session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                request.setAttribute("info", "Вы вышли :3");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break; 
                
            case "/addConsumer":
                request.getRequestDispatcher("/WEB-INF/addConsumer.jsp").forward(request, response);
                break;
                
            case "/createConsumer":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String cash = request.getParameter("cash");
                login = request.getParameter("login");
                password = request.getParameter("password");
                if("".equals(firstname) || firstname == null 
                        || "".equals(lastname) || lastname == null
                        || "".equals(login) || login == null
                        || "".equals(password) || password == null
                        || "".equals(cash) || cash == null){
                    request.setAttribute("info","Заполните все поля формы");
                    request.setAttribute("firstname",firstname);
                    request.setAttribute("lastname",lastname);
                    request.setAttribute("cash",cash);
                    request.getRequestDispatcher("/WEB-INF/addConsumer.jsp").forward(request, response);
                    break; 
                }
                Consumer consumer = new Consumer(firstname, lastname, Integer.parseInt(cash));
                consumerFacade.create(consumer);
                user = new User(login, password, consumer);
                userFacade.create(user);
                request.setAttribute("info","Добавлен покупатель: " +consumer.toString() );
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listProducts":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/WEB-INF/listProducts.jsp").forward(request, response);
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
