package dao;

import dto.ProductoDto;
import dto.TipoDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import sql.Conexion;

public class ProductoDaoImp implements ProductoDao {

    @Override
    public boolean agregar(ProductoDto dto) {
        try {

            Connection conexion = Conexion.getConexion();

            String query = "INSERT INTO producto (nombre_producto, codigo_tipo, precio_producto) VALUES (?, ?, ?);";

            PreparedStatement insertar = conexion.prepareStatement(query);

            insertar.setString(1, dto.getNombreProducto());
            insertar.setInt(2, dto.getCodigoTipo());
            insertar.setInt(3, dto.getPrecioProducto());
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
            String query1= "SET FOREIGN_KEY_CHECKS=0";
            String query2 = "DELETE FROM producto WHERE codigo_producto=?";
            String query3 = "SET FOREIGN_KEY_CHECKS=1";
            PreparedStatement  eliminarFK = conexion.prepareStatement(query1);
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
    public boolean modificar(ProductoDto dto) {
        try {

            Connection conexion = Conexion.getConexion();

            String query = "update producto set nombre_producto=?, codigo_tipo=?, precio_producto=? where codigo_producto=?";

            PreparedStatement modificar = conexion.prepareStatement(query);

            modificar.setString(1, dto.getNombreProducto());
            modificar.setInt(2, dto.getCodigoTipo());
            modificar.setInt(3, dto.getPrecioProducto());
            modificar.setInt(4, dto.getCodigoProducto());
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
    public ArrayList<ProductoDto> listar() {
        ArrayList<ProductoDto> lista = new ArrayList<ProductoDto>();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM producto";
            PreparedStatement buscar = conexion.prepareStatement(query);

            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                ProductoDto dto = new ProductoDto();
                dto.setCodigoProducto(rs.getInt("codigo_producto"));
                dto.setNombreProducto(rs.getString("nombre_producto"));
                dto.setPrecioProducto(rs.getInt("precio_producto"));
                dto.setCodigoTipo(rs.getInt("codigo_tipo"));
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
    public ArrayList<ProductoDto> ListarPorTipo(int codigo) {
        ArrayList<ProductoDto> lista = new ArrayList<ProductoDto>();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM producto where codigo_tipo=?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setInt(1, codigo);
            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                ProductoDto dto = new ProductoDto();
                dto.setCodigoProducto(rs.getInt("codigo_producto"));
                dto.setNombreProducto(rs.getString("nombre_producto"));
                dto.setPrecioProducto(rs.getInt("precio_producto"));
                dto.setCodigoTipo(rs.getInt("codigo_tipo"));
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
    public ProductoDto BuscarProducto(int codigo) {
        ProductoDto dto = new ProductoDto();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "select * from producto where codigo_producto = ?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setInt(1, codigo);
            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {

                dto.setCodigoProducto(rs.getInt("codigo_producto"));
                dto.setNombreProducto(rs.getString("nombre_producto"));
                dto.setPrecioProducto(rs.getInt("precio_producto"));
                dto.setCodigoTipo(rs.getInt("codigo_tipo"));

            }
            buscar.close();
            conexion.close();
        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return dto;
    }
    
    public Map<Integer, ArrayList<ProductoDto>> produtosPorTipo(){
        Map<Integer, ArrayList<ProductoDto>> diccionario = new HashMap<>();
        ArrayList<TipoDto> tipos = new TipoDaoImp().listar();
        
        for (TipoDto tipo : tipos) {
            ArrayList<ProductoDto> productos = ListarPorTipo(tipo.getCodigo());
            diccionario.put(tipo.getCodigo(), productos);
        }
        
        return diccionario;
    }
}
