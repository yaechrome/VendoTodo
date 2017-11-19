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
public class PerfilDto {
 private int codigoPerfil;
 private String nombrePerfil;

    public PerfilDto() {
    }

    public int getCodigoPerfil() {
        return codigoPerfil;
    }

    public void setCodigoPerfil(int codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codigoPerfil;
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
        final PerfilDto other = (PerfilDto) obj;
        if (this.codigoPerfil != other.codigoPerfil) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PerfilDto{" + "codigoPerfil=" + codigoPerfil + ", nombrePerfil=" + nombrePerfil + '}';
    }
 
 
}
