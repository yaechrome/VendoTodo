package dao;

import dto.DetalleVentaDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sql.Conexion;

public class DetalleVentaDaoImp implements DetalleVentaDao {

    @Override
    public boolean agregar(DetalleVentaDto dto) {
        try {

            Connection conexion = Conexion.getConexion();

            String query = "INSERT INTO detalle_venta ( codigo_producto, cantidad, total, codigo_venta) VALUES ( ?, ?, ?, ?)";

            PreparedStatement insertar = conexion.prepareStatement(query);

            insertar.setInt(1, dto.getCodigoProducto());
            insertar.setInt(2, dto.getCantidad());
            insertar.setInt(3, dto.getTotal());
            insertar.setInt(4, dto.getCodigoVenta());

            insertar.execute();
            insertar.close();
            conexion.close();
            return true;
        } catch (SQLException w) {
            System.out.println("Error SQL al agregar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al agregar " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminar(int producto, int venta) {
        try {

            Connection conexion = Conexion.getConexion();
            
            String query = "DELETE FROM detalle_venta WHERE codigo_producto=? and codigo_venta=?";
            
            PreparedStatement eliminar = conexion.prepareStatement(query);

            eliminar.setInt(1, producto);
            eliminar.setInt(2, venta);
            eliminar.execute();
            eliminar.close();

            conexion.close();
            return true;
        } catch (SQLException w) {
            System.out.println("Error SQL al eliminar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modificar(DetalleVentaDto dto) {
        try {

            Connection conexion = Conexion.getConexion();

            String query = "UPDATE detalle_venta SET codigo_producto=?, cantidad=?, total=?, codigo_venta=? where codigo_venta=? and codigo_producto=?";

            PreparedStatement modificar = conexion.prepareStatement(query);

            modificar.setInt(1, dto.getCodigoProducto());
            modificar.setInt(2, dto.getCantidad());
            modificar.setInt(3, dto.getTotal());
            modificar.setInt(4, dto.getCodigoVenta());
            modificar.setInt(5, dto.getCodigoVenta());
            modificar.setInt(6, dto.getCodigoProducto());

            modificar.execute();
            modificar.close();
            conexion.close();
            return true;
        } catch (SQLException w) {
            System.out.println("Error SQL al modificar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al modificar " + e.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList<DetalleVentaDto> listar() {
        ArrayList<DetalleVentaDto> lista = new ArrayList<DetalleVentaDto>();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM detalle_venta";
            PreparedStatement buscar = conexion.prepareStatement(query);
            
            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                DetalleVentaDto dto = new DetalleVentaDto();
                dto.setCodigoProducto(rs.getInt("codigo_producto"));
                dto.setCantidad(rs.getInt("cantidad"));
                dto.setTotal(rs.getInt("total"));
                dto.setCodigoVenta(rs.getInt("codigo_venta"));
                lista.add(dto);
            }
            buscar.close();
            conexion.close();
        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return lista; 
    }

    @Override
    public ArrayList<DetalleVentaDto> ListarPorVentas(int codigo) {
        ArrayList<DetalleVentaDto> lista = new ArrayList<DetalleVentaDto>();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM detalle_venta where codigo_venta =?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setInt(1, codigo);
            
            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                DetalleVentaDto dto = new DetalleVentaDto();
                dto.setCodigoProducto(rs.getInt("codigo_producto"));
                dto.setCantidad(rs.getInt("cantidad"));
                dto.setTotal(rs.getInt("total"));
                dto.setCodigoVenta(rs.getInt("codigo_venta"));
                lista.add(dto);
            }
            buscar.close();
            conexion.close();
        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return lista; 
    }

}
