package dao;

import dto.VentasDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sql.Conexion;

public class VentasDaoImp implements VentasDao {

    @Override
    public boolean ValidarVentaExiste(int codigo) {
        try {
            Connection conexion = Conexion.getConexion();
            String query = "select * from ventas where codigo_venta = ?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setInt(1, codigo);
            buscar.execute();
            ResultSet rs = buscar.executeQuery();
            if(rs.next()) {
                return true;
            }
            buscar.close();
            conexion.close();
            return false;
        } catch (SQLException w) {
            System.out.println("Error  " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean agregar(VentasDto dto) {
        try {
            Connection conexion = Conexion.getConexion();

            String query = "insert into ventas (codigo_vendedor,fecha_venta,total_venta) values (?,?,?)";

            PreparedStatement insertar = conexion.prepareStatement(query);

            insertar.setInt(1, dto.getCodigoVendedor());
            insertar.setDate(2, new java.sql.Date(dto.getFechaVenta().getTime()));
            insertar.setInt(3, dto.getTotalVenta());

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
    public boolean eliminar(int codigo) {
        try {

            Connection conexion = Conexion.getConexion();
            String query1 = "SET FOREIGN_KEY_CHECKS=0";
            String query2 = "DELETE FROM ventas WHERE codigo_venta=?";
            String query3 = "SET FOREIGN_KEY_CHECKS=1";
            PreparedStatement eliminarFK = conexion.prepareStatement(query1);
            PreparedStatement eliminar = conexion.prepareStatement(query2);
            PreparedStatement agregarFK = conexion.prepareStatement(query3);

            eliminarFK.execute();
            eliminarFK.close();

            eliminar.setInt(1, codigo);
            eliminar.execute();
            eliminar.close();

            agregarFK.execute();
            agregarFK.close();

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
    public boolean modificar(VentasDto dto) {
        try {
            Connection conexion = Conexion.getConexion();

            String query = "update ventas set codigo_vendedor=? ,fecha_venta=?,total_venta=? where codigo_venta =? ";

            PreparedStatement modificar = conexion.prepareStatement(query);

            modificar.setInt(1, dto.getCodigoVendedor());
            modificar.setDate(2, new java.sql.Date(dto.getFechaVenta().getTime()));
            modificar.setInt(3, dto.getTotalVenta());
            modificar.setInt(4, dto.getCodigoVenta());

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
    public ArrayList<VentasDto> listar() {
        ArrayList<VentasDto> lista = new ArrayList<VentasDto>();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM ventas";
            PreparedStatement buscar = conexion.prepareStatement(query);

            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                VentasDto dto = new VentasDto();
                dto.setCodigoVendedor(rs.getInt("codigo_vendedor"));
                dto.setFechaVenta(rs.getDate("fecha_venta"));
                dto.setTotalVenta(rs.getInt("total"));
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
    public String VendedorMasVentas() {
        String mensaje = "";
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT login_usuario, COUNT(codigo_venta) AS Total from usuarios join ventas on ventas.codigo_vendedor = usuarios.id_usuario group by codigo_vendedor order by Total desc limit 1";
            PreparedStatement buscar = conexion.prepareStatement(query);
            ResultSet rs = buscar.executeQuery();
            if (rs.next()) {
                mensaje = mensaje + "El vendedor " + rs.getString("login_usuario") + " realizó " + rs.getInt("Total") + " ventas. \n";
            }

        } catch (Exception e) {
            System.out.println("Error al buscar informacion " + e.getMessage());
        }
        return mensaje;
    }

    @Override
    public int codigoNuevaVenta() {
        int mensaje = 0;

        try {
            Connection conexion = Conexion.getConexion();
            String query = "select max(codigo_venta) as maximo from ventas";
            PreparedStatement buscar = conexion.prepareStatement(query);
            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                mensaje = rs.getInt("maximo") + 1;
            }
            buscar.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error al buscar informacion " + e.getMessage());
        }
        return mensaje;
    }

}
