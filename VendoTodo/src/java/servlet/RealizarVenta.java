package servlet;

import dao.DetalleVentaDaoImp;
import dao.ProductoDaoImp;
import dao.TipoDaoImp;
import dao.UsuarioDaoImp;
import dao.VentasDaoImp;
import dto.DetalleVentaDto;
import dto.ProductoDto;
import dto.TipoDto;
import dto.UsuarioDto;
import dto.VentasDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConstanteUtil;

@WebServlet(name = "RealizarVenta", urlPatterns = {"/privado/RealizarVenta"})
public class RealizarVenta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String mensaje = "";
        if ("GET".equals(request.getMethod())) {

            request.getRequestDispatcher(
                    "/paginas/crearVenta.jsp").
                    forward(request, response);
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            VentasDto venta = new VentasDto();
            UsuarioDto usuarioDto = (UsuarioDto) request.getSession().getAttribute(ConstanteUtil.LOGIN_USUARIO);
            venta.setCodigoVendedor(usuarioDto.getIdUsuario());

            venta.setFechaVenta(new Date());
            venta.setTotalVenta(0);

            if (new VentasDaoImp().agregar(venta)) {

                request.setAttribute("codigo_venta", venta.getCodigoVenta());
                request.getRequestDispatcher("/paginas/crearVenta.jsp")
                        .forward(request, response);
            } else {
                mensaje = "No se pudo agregar";
                request.setAttribute("msg", mensaje);
                request.getRequestDispatcher("/paginas/crearVenta.jsp")
                        .forward(request, response);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            request.setAttribute("msg", mensaje);
            request.getRequestDispatcher("/paginas/crearVenta.jsp")
                    .forward(request, response);
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
