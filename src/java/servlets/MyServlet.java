/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Consumer;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ConsumerFacade;
import session.ProductFacade;

/**
 *
 * @author A
 */
@WebServlet(name = "MyServlet", urlPatterns = {
    "/MyServlet",
    "/addProduct",
    "/createProduct",
    "/addConsumer",
    "/createConsumer",
    "/listProducts",
    "/listConsumers",
    "/buyProductForm",
    "/buyProduct",
    "/addMoneyToConsumer",
    "/addMoney"
        })
public class MyServlet extends HttpServlet {
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
        String path = request.getServletPath();
            
        switch (path) {
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
            case "/addConsumer":
                request.getRequestDispatcher("/WEB-INF/addConsumer.jsp").forward(request, response);
                break;
            case "/createConsumer":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String cash = request.getParameter("cash");
                if("".equals(firstname) || firstname == null 
                        || "".equals(lastname) || lastname == null
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
                request.setAttribute("info","Добавлен покупатель: " +consumer.toString() );
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listProducts":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/WEB-INF/listProducts.jsp").forward(request, response);
                break;    
            case "/listConsumers":
                List<Consumer> listConsumers = consumerFacade.findAll();
                request.setAttribute("listConsumers", listConsumers);
                request.getRequestDispatcher("/WEB-INF/listConsumers.jsp").forward(request, response);
                break;    
            case "/buyProductForm":
                listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                listConsumers = consumerFacade.findAll();
                request.setAttribute("listConsumers", listConsumers);
                request.getRequestDispatcher("/WEB-INF/buyProductForm.jsp").forward(request, response);
                break;
            case "/buyProduct":
                String productId = request.getParameter("productId");                
                String consumerId = request.getParameter("consumerId");              
                String count = request.getParameter("count");
                
                if(consumerId == null || "".equals(consumerId) 
                        || productId == null || "".equals(productId) 
                        || count == null || "".equals(count)){
                    
                    request.setAttribute("info", "Заполните все поля");
                    listProducts = productFacade.findAll();
                    request.setAttribute("listProducts", listProducts);
                    listConsumers = consumerFacade.findAll();
                    request.setAttribute("listConsumers", listConsumers);
                    request.getRequestDispatcher("/WEB-INF/buyProductForm.jsp").forward(request, response);
                    break;
                }
                product = productFacade.find(Long.parseLong(productId));
                consumer = consumerFacade.find(Long.parseLong(consumerId));
                if (consumer.getCash()<product.getPrice()){
                    request.setAttribute("info","Недостаточно средств");
                    listProducts = productFacade.findAll();
                    request.setAttribute("listProducts", listProducts);
                    listConsumers = consumerFacade.findAll();
                    request.setAttribute("listConsumers", listConsumers);
                    request.getRequestDispatcher("/WEB-INF/buyProductForm.jsp").forward(request, response);
                    break;
                }
                if (product.getQuantity()-Integer.parseInt(count)<0){
                    request.setAttribute("info","Нету такого количества товара");
                    listProducts = productFacade.findAll();
                    request.setAttribute("listProducts", listProducts);
                    listConsumers = consumerFacade.findAll();
                    request.setAttribute("listConsumers", listConsumers);
                    request.getRequestDispatcher("/WEB-INF/buyProductForm.jsp").forward(request, response);
                    break;
                }
                consumer.setCash(consumer.getCash()-(product.getPrice()*Integer.parseInt(count)));
                consumerFacade.edit(consumer);
                product.setQuantity(product.getQuantity()-Integer.parseInt(count));
                productFacade.edit(product);
                request.setAttribute("info","Товар куплен");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/addMoneyToConsumer":
                listConsumers = consumerFacade.findAll();
                request.setAttribute("listConsumers", listConsumers);
                request.getRequestDispatcher("/WEB-INF/addMoneyToConsumer.jsp").forward(request, response);
                break;
            case "/addMoney":
                String money = request.getParameter("money");
                consumerId = request.getParameter("consumerId");
                if(consumerId == null || "".equals(consumerId) || money == null || "".equals(money)){
                    
                    request.setAttribute("info", "Заполните все поля");
                    listConsumers = consumerFacade.findAll();
                    request.setAttribute("listConsumers", listConsumers);
                    request.getRequestDispatcher("/WEB-INF/addMoneyToConsumer.jsp").forward(request, response);
                    break;
                }
                consumer = consumerFacade.find(Long.parseLong(consumerId));
                
                consumer.setCash(consumer.getCash()+Integer.parseInt(money));
                consumerFacade.edit(consumer);
                request.setAttribute("info", "Пополнен кошелек");
                request.getRequestDispatcher("index.jsp").forward(request, response);
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
