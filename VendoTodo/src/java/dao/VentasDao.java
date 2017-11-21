
package dao;
import dto.*;

public interface VentasDao extends BaseDao<VentasDto> {
    
    public boolean ValidarVentaExiste(int codigo);
    public String VendedorMasVentas();
    public int codigoNuevaVenta();
    public boolean actualizarTotal(int codigo_venta);
}
