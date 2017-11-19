/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ProductoDto;
import java.util.ArrayList;

/**
 *
 * @author yaechrome
 */
public interface ProductoDao extends BaseDao<ProductoDto>{
    public ArrayList<ProductoDto> ListarPorTipo(int codigo);
    public ProductoDto BuscarProducto (int codigo);
    
}
