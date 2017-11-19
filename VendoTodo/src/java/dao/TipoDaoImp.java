
package dao;



import dto.TipoDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sql.Conexion;

/**
 *
 * @author yaechrome
 */
public class TipoDaoImp implements TipoDao{

    @Override
    public ArrayList<TipoDto> listar() {
        ArrayList<TipoDto> lista = new ArrayList<TipoDto>();        
        try{
            Connection conexion=Conexion.getConexion();
            String query="SELECT * FROM tipo";
            PreparedStatement buscar=conexion.prepareStatement(query);
            
            ResultSet rs = buscar.executeQuery();
            
            while(rs.next()){
                TipoDto dto = new TipoDto();
                dto.setCodigo(rs.getInt("codigo_tipo"));
                dto.setNombreTipo(rs.getString("nombre_tipo"));
                lista.add(dto);
            }
            buscar.close();
            conexion.close();
        }catch(SQLException w ){
            System.out.println("Error SQL al buscar "+w.getMessage());
        }catch(Exception e){
            System.out.println("Error al buscar "+e.getMessage());
        }
        return lista;
    }

     @Override
    public String TipoMasVendido() {

        String mensaje = "";
        String nombre = "";
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT codigo_tipo , MAX(Total) FROM (SELECT codigo_tipo, COUNT(codigo_tipo) AS Total \n"
                    + "from detalle_venta join producto on detalle_venta.codigo_producto = producto.codigo_producto\n"
                    + "group by codigo_Tipo) as maximo";
            PreparedStatement buscar = conexion.prepareStatement(query);
            ResultSet rs = buscar.executeQuery();
            if (rs.next()) {
                nombre = BuscarNombreTipo(rs.getInt("codigo_tipo"));
                mensaje = mensaje + "El tipo " + nombre + " fue el más vendido, con " + rs.getInt("MAX(Total)") + " ventas. \n";
            }

        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return mensaje;
    }

    @Override
    public String BuscarNombreTipo(int codigoTipo) {
        String mensaje ="";
        try {
            Connection conexion = Conexion.getConexion();
            String query = "select nombre_tipo from tipo where codigo_tipo=?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setInt(1, codigoTipo);
            buscar.execute();
            ResultSet rs= buscar.executeQuery();
            if (rs.next()) {
                mensaje= rs.getString("nombre_tipo");
            }
            
            
            buscar.close();
            conexion.close();
        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return mensaje;
        
    }
    
    
    
}
