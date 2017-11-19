/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nippo
 */
@WebServlet(name = "RealizarDetalleVenta", urlPatterns = {"/RealizarDetalleVenta"})
public class RealizarDetalleVenta extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String datos = "{";
        Map<Integer, ArrayList<ProductoDto>> tipos = new ProductoDaoImp().produtosPorTipo();
        for (Map.Entry<Integer, ArrayList<ProductoDto>> tipo : tipos.entrySet()) {
            datos += "\"" + tipo.getKey() + "\": [";
            for (ProductoDto producto : tipo.getValue()) {
                datos += "{ nombre: \"" + producto.getNombreProducto().replace("\"", "\\\"") + "\", codigo: \"" + producto.getCodigoProducto() + "\", precio: \"" + producto.getPrecioProducto() + "\"},";
            }
            datos += "],";
        }
        datos += "}";

        request.setAttribute("datos", datos);       
        request.setAttribute("tipos", new TipoDaoImp().listar());
            
        if ("GET".equals(request.getMethod())) {
            request.getRequestDispatcher(
                    "paginas/realizarVenta.jsp").
                    forward(request, response);
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<DetalleVentaDto> lista = new ArrayList<>();
        try (PrintWriter out = response.getWriter()) {
            
            
            String mensaje = "";
            
            
            String cmbProductos = request.getParameter("cmbProductos").trim();
            String cmbTipo = request.getParameter("cmbTipos").trim();
            String txtCantidad = request.getParameter("txtCantidad").trim();
            
            if (cmbProductos.isEmpty() || cmbTipo.isEmpty() || txtCantidad.isEmpty() ) {
                mensaje = "No se ha podido crear usuario verifique se hayan ingresado todos los datos";
            } else {
                
                DetalleVentaDto dto = new DetalleVentaDto();
                ProductoDto prod = new ProductoDaoImp().BuscarProducto(Integer.parseInt(cmbProductos));
                dto.setCodigoProducto(Integer.parseInt(cmbTipo));
                dto.setCantidad(Integer.parseInt(txtCantidad));
                int precio = prod.getPrecioProducto();
                dto.setTotal(Integer.parseInt(txtCantidad)*precio);
                dto.setCodigoVenta(new VentasDaoImp().codigoNuevaVenta());
                lista.add(dto);
            }
            request.setAttribute("lista", lista);
            request.setAttribute("msg", mensaje);
            request.getRequestDispatcher(
                    "paginas/realizarVenta.jsp").
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
