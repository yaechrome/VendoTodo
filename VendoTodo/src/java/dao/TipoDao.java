/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import dto.TipoDto;
import java.util.ArrayList;

/**
 *
 * @author yaechrome
 */
public interface TipoDao {
    public ArrayList<TipoDto> listar();
    public String TipoMasVendido();
    public String BuscarNombreTipo(int codigo);
}
