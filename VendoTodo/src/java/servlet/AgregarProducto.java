/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ProductoDaoImp;
import dao.TipoDaoImp;
import dto.ProductoDto;
import dto.TipoDto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yaechrome
 */
@WebServlet(name = "AgregarProducto", urlPatterns = {"/privado/AgregarProducto"})
public class AgregarProducto extends HttpServlet {

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

        request.setAttribute("tipos", new TipoDaoImp().listar());
        if ("GET".equals(request.getMethod())) {
            request.getRequestDispatcher("/paginas/agregarProducto.jsp").forward(request, response);
            return;
        }
        response.setContentType("text/html;charset=UTF-8");

        String eliminar = request.getParameter("btnEliminar");
        if (eliminar != null) {
            String mensaje = "";
            try {

                int id = Integer.parseInt(eliminar);
                if (new ProductoDaoImp().eliminar(id)) {
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
            ArrayList<ProductoDto> lista = new ProductoDaoImp().listar();
            if (!lista.isEmpty()) {
                request.setAttribute("lista", lista);
            }
            request.getRequestDispatcher(
                    "/paginas/agregarProducto.jsp").
                    forward(request, response);
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            String mensaje = "";

            String txtNombre = request.getParameter("txtNombre").trim();
            String txtPrecio = request.getParameter("txtPrecio").trim();
            int cmbTipo = Integer.parseInt(request.getParameter("cmbTipo"));
            String txtId = request.getParameter("txtId");
            String boton = request.getParameter("btn").trim();
            switch (boton) {
                case "Grabar":
                    if (txtNombre.isEmpty() || txtPrecio.isEmpty()) {
                        mensaje = "No se ha podido crear usuario verifique se hayan ingresado todos los datos";
                    } else {

                        try {
                            ProductoDto dto = new ProductoDto();

                            dto.setNombreProducto(txtNombre);
                            dto.setPrecioProducto(Integer.parseInt(txtPrecio));
                            dto.setCodigoTipo(cmbTipo);
                            if (new ProductoDaoImp().agregar(dto)) {
                                mensaje = "REGISTRO GRABADO!!";
                            } else {
                                mensaje = "NO SE GRABO!!";
                            }
                        } catch (NumberFormatException e) {
                            mensaje = "Error! el precio debe ser numérico";
                        }
                    }
                    break;
                case "Actualizar":
                    if (txtNombre.isEmpty() || txtPrecio.isEmpty() || txtId.isEmpty()) {
                        mensaje = "No se ha podido modificar usuario verifique se hayan ingresado todos los datos";
                    } else {

                        try {
                            ProductoDto dto = new ProductoDto();
                            dto.setCodigoProducto(Integer.parseInt(txtId));
                            dto.setNombreProducto(txtNombre);
                            dto.setPrecioProducto(Integer.parseInt(txtPrecio));
                            dto.setCodigoTipo(cmbTipo);
                            if (new ProductoDaoImp().modificar(dto)) {
                                mensaje = "REGISTRO ACTUALIZADO!!";
                            } else {
                                mensaje = "NO SE PUDO ACTUALIZAR!!";
                            }
                        } catch (NumberFormatException e) {
                            mensaje = "Error! el precio debe ser numérico";
                        }
                    }
                    break;
                case "Mostrar":
                    ArrayList<ProductoDto> lista = new ProductoDaoImp().listar();
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
            request.getRequestDispatcher(
                    "/paginas/agregarProducto.jsp").
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
