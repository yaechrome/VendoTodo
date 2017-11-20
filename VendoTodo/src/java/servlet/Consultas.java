/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.TipoDaoImp;
import dao.VentasDaoImp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static util.ConstanteUtil.CONSULTA_URL_FILE;

/**
 *
 * @author nippo
 */
@WebServlet(name = "Consultas", urlPatterns = {"/privado/Consultas"})
public class Consultas extends HttpServlet {

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
        request.getRequestDispatcher(CONSULTA_URL_FILE).forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String mensaje = "";
            String respuesta = "";

            String opcion = request.getParameter("consulta");
            if (opcion == null) {
                mensaje = "No se ha podido realizar la consulta verifique se haya seleccionado una opcion";
            } else {
                if (opcion.equals("Venta")) {
                    respuesta = new VentasDaoImp().VendedorMasVentas();
                }
                if (opcion.equals("Tipo")) {
                    respuesta = new TipoDaoImp().TipoMasVendido();
                }
            }

            if (!mensaje.isEmpty()) {
                request.setAttribute("msg", mensaje);
            }
            request.setAttribute("respuesta", respuesta);
            request.getRequestDispatcher(CONSULTA_URL_FILE).forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
