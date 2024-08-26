/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Usuario;
import model.dao.UsuarioDAO;

/**
 *
 * @author Senai
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller","/index","/login","/cadastro","/home","/logar"})
public class Controller extends HttpServlet {

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

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String paginaAtual = request.getServletPath();
        
        if(paginaAtual.equals("/login")) {
            
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            
        } else if(paginaAtual.equals("/cadastro")) {
            
            request.getRequestDispatcher("/WEB-INF/jsp/cadastro.jsp").forward(request, response);
            
        }else if(paginaAtual.equals("/home")) {
            
            request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
            
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = request.getServletPath();
        String nexPage = "";
        Usuario user = new Usuario();
        UsuarioDAO udao = new UsuarioDAO();
        
        if(url.equals("/logar")){
            user.setEmail(request.getParameter("email"));
            user.setSenha(request.getParameter("senha"));
            
            user = udao.validarLogin(user);
            if(user.getId_usuario() > 0) {
                response.sendRedirect("./home");
            } else {
                response.sendRedirect("./login");
            }
            
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
