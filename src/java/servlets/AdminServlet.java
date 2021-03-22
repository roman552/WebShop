/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Consumer;
import entity.User;
import entity.UserRole;
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
import session.RoleFacade;
import session.UserFacade;

/**
 *
 * @author A
 */
@WebServlet(name = "AdminServlet", urlPatterns = {
    "/listConsumers",
    "/setRoleToUser",
    "/adminPanel"
})
public class AdminServlet extends HttpServlet {
    @EJB
    ConsumerFacade consumerFacade;
    @EJB
    UserFacade userFacade;
    @EJB
    RoleFacade roleFacade;
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
        HttpSession session = request.getSession(false);
        
        if(session == null){
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response);
            request.setAttribute("info", "Войдите в систему");
            return;
        }
        User user = (User) session.getAttribute("user");
        if(user == null){
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response);
            request.setAttribute("info", "Войдите в систему");
            return;
        }
        
        if (!"ADMIN".equals(user.getRole().getRoleName())) {
            request.getRequestDispatcher("/WEB-INF/showLoginForm.jsp").forward(request, response);
            request.setAttribute("info", "Войдите в систему");
            return;
        }
        
        String path = request.getServletPath();
        
        switch(path) {
            case "/listConsumers":
                List<Consumer> consumers = consumerFacade.findAll();
                request.setAttribute("listConsumers", consumers);
                request.getRequestDispatcher("/WEB-INF/listConsumers.jsp").forward(request, response);
                break;
            case "/adminPanel":
                List<User> users = userFacade.findAll();
                request.setAttribute("listUsers", users);
                List<UserRole> roles = roleFacade.findAll();
                request.setAttribute("listRoles", roles);
                request.getRequestDispatcher("/WEB-INF/adminPanel.jsp").forward(request, response);
                break;
            case "/setRoleToUser":
                String userId = request.getParameter("userId");
                String roleId = request.getParameter("roleId");
                
                if("".equals(userId) || userId == null 
                        || "".equals(roleId) || roleId == null) {
                    request.setAttribute("info","Заполните все поля формы");
                    request.getRequestDispatcher("/WEB-INF/adminPanel.jsp").forward(request, response);
                }
                
                user = userFacade.find(Long.parseLong(userId));
                UserRole role = roleFacade.find(Long.parseLong(roleId));
                
                
                
                user.setRole(role);
                userFacade.edit(user);
                
                request.setAttribute("info","Роль изменена");
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
