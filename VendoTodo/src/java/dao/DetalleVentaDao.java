
package dao;

import dto.DetalleVentaDto;
import java.util.ArrayList;


public interface DetalleVentaDao {
    public boolean agregar(DetalleVentaDto dto);
    
    public boolean eliminar(int producto, int venta);
    
    public boolean modificar(DetalleVentaDto dto);
    
    public ArrayList<DetalleVentaDto> listar();
    
    public ArrayList<DetalleVentaDto> ListarPorVentas (int codigo);
}
