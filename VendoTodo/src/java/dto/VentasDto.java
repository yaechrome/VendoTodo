/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author CETECOM
 */
public class VentasDto {
    private int codigoVenta;
    private Date fechaVenta;
    private int totalVenta;   
    private int codigoVendedor;

    public VentasDto() {
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(int totalVenta) {
        this.totalVenta = totalVenta;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.codigoVenta;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VentasDto other = (VentasDto) obj;
        if (this.codigoVenta != other.codigoVenta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VentasDto{" + "codigoVenta=" + codigoVenta + ", fechaVenta=" + fechaVenta + ", totalVenta=" + totalVenta + ", codigoVendedor=" + codigoVendedor + '}';
    }
    
    
}
