/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.PerfilDaoImp;
import dao.UsuarioDaoImp;
import dto.PerfilDto;
import dto.UsuarioDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yaechrome
 */
@WebServlet(name = "AgregarUsuario", urlPatterns = {"/privado/AgregarUsuario"})
public class AgregarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("GET".equals(request.getMethod())) {
            ArrayList<PerfilDto> perfiles = new PerfilDaoImp().listar();
            request.setAttribute("perfiles", perfiles);
            request.getRequestDispatcher(
                    "paginas/agregarUsuario.jsp").
                    forward(request, response);
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String mensaje="";
            String txtLogin = request.getParameter("txtLogin").trim();
            String txtPass = request.getParameter("txtPass").trim();
            String txtNombre = request.getParameter("txtNombre").trim();
            String txtApellido = request.getParameter("txtApellido").trim();
            String txtCorreo = request.getParameter("txtCorreo").trim();
            String txtFecha =request.getParameter("dtFecha").trim();
            
                    
            if (txtLogin.isEmpty() || txtPass.isEmpty() || txtNombre.isEmpty()
                    || txtApellido.isEmpty() || txtCorreo.isEmpty() || txtFecha.isEmpty()) {
                mensaje="No se ha podido crear usuario verifique se hayan ingresado todos los datos";
            }else {
                
                if(new UsuarioDaoImp().ValidarLogin(txtLogin) ){
                    mensaje = "Login ya existe!";
                    
                }else{
                    
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dtfecha = dateFormat.parse(txtFecha);
                    UsuarioDto dto = new UsuarioDto();
                    String pass = new UsuarioDaoImp().Encriptar(txtPass);

                    dto.setLoginUsuario(txtLogin);
                    dto.setPassUsuario(pass);
                    dto.setNombreUsuario(txtNombre);
                    dto.setApellidoUsuario(txtApellido);
                    dto.setCorreoUsuario(txtCorreo);
                    dto.setCodigoPerfil(Integer.parseInt(request.getParameter("cmbPerfil")));
                    dto.setFechaNacimiento(dtfecha);

                    if (new UsuarioDaoImp().agregar(dto)) {
                        mensaje = "REGISTRO GRABADO!!";
                    } else {
                        mensaje = "NO SE GRABO!!";
                    }
                }
                
                
            }

            request.setAttribute("msg", mensaje);
            request.setAttribute("perfiles", new PerfilDaoImp().listar());
            request.getRequestDispatcher(
                    "paginas/agregarUsuario.jsp").
                    forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
