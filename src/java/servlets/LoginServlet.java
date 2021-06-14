/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Consumer;
import entity.Product;
import entity.UserRole;
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
import session.RoleFacade;
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
    "/main",
    "",
    "/logout"
})
public class LoginServlet extends HttpServlet {
    @EJB
    private UserFacade userFacade;
    @EJB 
    private ConsumerFacade consumerFacade;
    @EJB 
    private ProductFacade productFacade;
    @EJB 
    private RoleFacade roleFacade;
    HttpSession session;
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
        
        if (roleFacade.findAll().isEmpty()) {
            UserRole consumerRole = new UserRole("CONSUMER");
            UserRole managerRole = new UserRole("MANAGER");
            UserRole adminRole = new UserRole("ADMIN");
            
            roleFacade.create(consumerRole);
            roleFacade.create(managerRole);
            roleFacade.create(adminRole);
            
            Consumer consumer = new Consumer("Roman", "Ivanov", 0);
            consumerFacade.create(consumer);
            
            User user = new User("admin", "admin", consumer, adminRole);
            userFacade.create(user);
        }
        
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
                    request.getRequestDispatcher("/main").forward(request, response);
                    break;
                }
                
                if (!password.equals(user.getPassword())) {
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/main").forward(request, response);
                    break;
                }
                
                session = request.getSession(true);
                session.setAttribute("user", user);   
                session.setAttribute("login", user.getLogin()); 
                session.setAttribute("cash", user.getUser().getCash()); 
                session.setAttribute("role", user.getRole().getRoleName()); 
                request.setAttribute("login", user.getLogin());
                request.setAttribute("cash", user.getUser().getCash());
                request.getRequestDispatcher("/main").forward(request, response);
                break;
                
            case "/logout":
                session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                request.setAttribute("info", "Вы вышли :3");
                request.getRequestDispatcher("/").forward(request, response);
                break; 
                
            case "/addConsumer":
                request.getRequestDispatcher("/WEB-INF/addConsumer.jsp").forward(request, response);
                break;
                
            case "/createConsumer":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                
                login = request.getParameter("login");
                password = request.getParameter("password");
                if("".equals(firstname) || firstname == null 
                        || "".equals(lastname) || lastname == null
                        || "".equals(login) || login == null
                        || "".equals(password) || password == null
                        ){
                    request.setAttribute("info","Заполните все поля формы");
                    request.setAttribute("firstname",firstname);
                    request.setAttribute("lastname",lastname);
                    
                    request.getRequestDispatcher("/WEB-INF/addConsumer.jsp").forward(request, response);
                    break; 
                }
                Consumer consumer = new Consumer(firstname, lastname, 0);
                consumerFacade.create(consumer);
                UserRole userRole = roleFacade.findByName("CONSUMER");
                
                user = new User(login, password, consumer, userRole);
                userFacade.create(user);
                request.setAttribute("info","Регистрация завершина");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/main":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                
                
                
                try {
                    if (session != null) {   
                        User userData = (User) session.getAttribute("user");
                        int money = (int) session.getAttribute("cash");
                        String role = (String) session.getAttribute("role");
                        request.setAttribute("login", userData.getLogin());
                        request.setAttribute("cash", money);
                        request.setAttribute("role", role);
                        
                    } 
                    
                } catch (Exception e) {
                }
                
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "":
                listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts); 
                request.getRequestDispatcher("index.html").forward(request, response);
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
