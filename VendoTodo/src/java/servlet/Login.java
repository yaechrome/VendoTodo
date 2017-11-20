/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.UsuarioDaoImp;
import dto.UsuarioDto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.ConstanteUtil;

/**
 *
 * @author nippo
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    private void iniciarSesion(UsuarioDto usuario, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(ConstanteUtil.LOGIN_USUARIO, usuario);
    }

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
        try (PrintWriter out = response.getWriter()) {
            String mensajeError = "default message";
            boolean error = false;
            String nombreLogin = request.getParameter("user").trim();
            String pass = request.getParameter("pass").trim();
            if (!new UsuarioDaoImp().ValidarLogin(nombreLogin)) {
                mensajeError = "Usuario no existe";
                error = true;
            }

            if (!new UsuarioDaoImp().ValidarPassword(nombreLogin, new UsuarioDaoImp().Encriptar(pass))) {
                mensajeError = "Clave incorrecta";
                error = true;
            }

            if (!error) {
                iniciarSesion(new UsuarioDaoImp().BuscarUsuario(nombreLogin), request);
                response.sendRedirect(request.getContextPath() + ConstanteUtil.HOME_URL_SERVLET);
            } else {
                request.setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher(ConstanteUtil.LOGIN_URL_FILE).forward(request, response);
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
        request.getRequestDispatcher(ConstanteUtil.LOGIN_URL_FILE).forward(request, response);
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
