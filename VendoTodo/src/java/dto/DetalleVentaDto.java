/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author CETECOM
 */
public class DetalleVentaDto {
    private int cantidad;
    private int total;
    private int codigoProducto;
    private int codigoVenta;

    public DetalleVentaDto() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    @Override
    public String toString() {
        return "DetalleVentaDto{" + "cantidad=" + cantidad + ", total=" + total + ", codigoProducto=" + codigoProducto + ", codigoVenta=" + codigoVenta + '}';
    }
    
    
}
