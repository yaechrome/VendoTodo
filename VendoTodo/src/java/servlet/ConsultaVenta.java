/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DetalleVentaDaoImp;
import dao.VentasDaoImp;
import dto.DetalleVentaDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ConsultaVenta", urlPatterns = {"/ConsultaVenta"})
public class ConsultaVenta extends HttpServlet {

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
        if ("GET".equals(request.getMethod())) {

            request.getRequestDispatcher(
                    "/paginas/consulta.jsp").
                    forward(request, response);
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            String mensaje = "";
            ArrayList<DetalleVentaDto> listaDesplegar = new ArrayList<>();

            String venta = request.getParameter("txtVenta").trim();
            if (venta.isEmpty()) {
                mensaje = "No se ha podido realizar la consulta verifique se haya ingresado un N° de venta válido";
            } else {
                try {
                    int numero = Integer.parseInt(venta);
                    if (new VentasDaoImp().ValidarVentaExiste(numero)) {
                        listaDesplegar = new DetalleVentaDaoImp().ListarPorVentas(numero);
                    } else {
                        mensaje = "N° de venta no válido. Venta no existe";
                    }
                } catch (NumberFormatException e) {
                    mensaje = "Debe ingresar un numero";
                }
            }
            if (!mensaje.isEmpty()) {
                request.setAttribute("msg", mensaje);
            }
            if (!listaDesplegar.isEmpty()) {
                request.setAttribute("listaDesplegar", listaDesplegar);
            }
            request.getRequestDispatcher("/paginas/consulta.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
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
