
package servlet;

import dao.DetalleVentaDaoImp;
import dao.ProductoDaoImp;
import dao.TipoDaoImp;
import dao.UsuarioDaoImp;
import dto.DetalleVentaDto;
import dto.ProductoDto;
import dto.TipoDto;
import dto.UsuarioDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RealizarVenta", urlPatterns = {"/RealizarVenta"})
public class RealizarVenta extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        if ("GET".equals(request.getMethod())) {
            ArrayList<TipoDto> tipos = new TipoDaoImp().listar();
            //ArrayList<UsuarioDto> usuarios = new UsuarioDaoImp().ListarVendedores();
            
            request.setAttribute("tipos", tipos);
            request.getRequestDispatcher(
                    "/paginas/realizarVentas.jsp").
                    forward(request, response);
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ArrayList<ProductoDto> productos = new ProductoDaoImp().ListarPorTipo(2);
            request.setAttribute("productos", productos);
            String mensaje = "";
            
            String cmbTipo = request.getParameter("cmbTipo").trim();
            String cmbProductos = request.getParameter("cmbProductos").trim();
            String txtCantidad = request.getParameter("txtCantidad").trim();
            
            if (cmbProductos.isEmpty() || cmbTipo.isEmpty() || txtCantidad.isEmpty() ) {
                mensaje = "No se ha podido crear usuario verifique se hayan ingresado todos los datos";
            } else {
                
                DetalleVentaDto dto = new DetalleVentaDto();

                dto.setCodigoProducto(Integer.parseInt(cmbTipo));
                dto.setCantidad(Integer.parseInt(txtCantidad));
                ProductoDto prod = new ProductoDaoImp().BuscarProducto(Integer.parseInt(cmbProductos));
                int precio = prod.getPrecioProducto();
                dto.setTotal(Integer.parseInt(txtCantidad)*precio);
               // dto.setCodigoVenta();
                if (new DetalleVentaDaoImp().agregar(dto)) {
                    mensaje = "REGISTRO GRABADO!!";
                } else {
                    mensaje = "NO SE GRABO!!";
                }
            }
            request.setAttribute("msg", mensaje);
            request.setAttribute("tipos", new TipoDaoImp().listar());
            request.getRequestDispatcher(
                    "/paginas/realizarVenta.jsp").
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
