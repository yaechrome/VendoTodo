/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author CETECOM
 */
public interface BaseDao<T> {
    
    public boolean agregar(T dto);
    
    public boolean eliminar(int codigo);
    
    public boolean modificar(T dto);
    
    public ArrayList<T> listar();
    
    
}
