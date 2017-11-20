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
        request.setAttribute("perfiles", new PerfilDaoImp().listar());
        if ("GET".equals(request.getMethod())) {

            request.getRequestDispatcher(
                    "/paginas/agregarUsuario.jsp").
                    forward(request, response);
            return;
        }
        response.setContentType("text/html;charset=UTF-8");

        String eliminar = request.getParameter("btnEliminar");
        if (eliminar != null) {
            String mensaje = "";
            try {

                int id = Integer.parseInt(eliminar);
                if (new UsuarioDaoImp().eliminar(id)) {
                    mensaje = "REGISTRO ELIMINADO!!";
                } else {
                    mensaje = "NO SE PUDO ELIMINAR!!";
                }
            } catch (NumberFormatException e) {
                mensaje = "Error! el id debe ser numérico";
            }
            if (!mensaje.isEmpty()) {
                request.setAttribute("msg", mensaje);
            }
            ArrayList<UsuarioDto> lista = new UsuarioDaoImp().listar();
            if (!lista.isEmpty()) {
                request.setAttribute("lista", lista);
            }
            request.getRequestDispatcher(
                    "/paginas/agregarUsuario.jsp").
                    forward(request, response);
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            String mensaje = "";
            String txtLogin = request.getParameter("txtLogin").trim();
            String txtPass = request.getParameter("txtPass").trim();
            String txtNombre = request.getParameter("txtNombre").trim();
            String txtApellido = request.getParameter("txtApellido").trim();
            String txtCorreo = request.getParameter("txtCorreo").trim();
            String txtFecha = request.getParameter("dtFecha").trim();
            String txtId = request.getParameter("txtId");
            String boton = request.getParameter("btn").trim();
            switch (boton) {
                case "Grabar":
                    if (txtLogin.isEmpty() || txtPass.isEmpty() || txtNombre.isEmpty()
                            || txtApellido.isEmpty() || txtCorreo.isEmpty() || txtFecha.isEmpty()) {
                        mensaje = "No se ha podido crear usuario verifique se hayan ingresado todos los datos";
                    } else {

                        if (new UsuarioDaoImp().ValidarLogin(txtLogin)) {
                            mensaje = "Login ya existe!";

                        } else {

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
                    break;
                case "Actualizar":
                    if (txtLogin.isEmpty() || txtPass.isEmpty() || txtNombre.isEmpty()
                            || txtApellido.isEmpty() || txtCorreo.isEmpty() || txtFecha.isEmpty() || txtId.isEmpty()) {
                        mensaje = "No se ha podido crear usuario verifique se hayan ingresado todos los datos";
                    } else {

                            try {
                                int id = Integer.parseInt(txtId);
                        if (new UsuarioDaoImp().ValidarCambioLogin(id, txtLogin)) {
                            mensaje = "Login ya existe!";

                        } else {
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date dtfecha = dateFormat.parse(txtFecha);
                                UsuarioDto dto = new UsuarioDto();
                                String pass = new UsuarioDaoImp().Encriptar(txtPass);
                                dto.setIdUsuario(id);
                                dto.setLoginUsuario(txtLogin);
                                dto.setPassUsuario(pass);
                                dto.setNombreUsuario(txtNombre);
                                dto.setApellidoUsuario(txtApellido);
                                dto.setCorreoUsuario(txtCorreo);
                                dto.setCodigoPerfil(Integer.parseInt(request.getParameter("cmbPerfil")));
                                dto.setFechaNacimiento(dtfecha);

                                if (new UsuarioDaoImp().modificar(dto)) {
                                    mensaje = "REGISTRO ACTUALIZADO!!";
                                } else {
                                    mensaje = "NO SE PUDO ACTUALIZAR!!";
                                }
                        }
                            } catch (NumberFormatException e) {
                                mensaje = "Error! el debe ser numérico";
                            }
                    }

                    break;
                case "Mostrar":
                    ArrayList<UsuarioDto> lista = new UsuarioDaoImp().listar();
                    if (!lista.isEmpty()) {
                        request.setAttribute("lista", lista);
                    }
                    break;
                default:
                    throw new AssertionError();
            }

            if (!mensaje.isEmpty()) {
                request.setAttribute("msg", mensaje);
            }
            request.setAttribute("perfiles", new PerfilDaoImp().listar());
            request.getRequestDispatcher(
                    "/paginas/agregarUsuario.jsp").
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
