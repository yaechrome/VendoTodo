/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.PerfilDto;
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
public class PerfilDaoImp implements PerfilDao{

    @Override
    public ArrayList<PerfilDto> listar() {
        ArrayList<PerfilDto> lista = new ArrayList<PerfilDto>();        
        try{
            Connection conexion=Conexion.getConexion();
            String query="SELECT * FROM perfil";
            PreparedStatement buscar=conexion.prepareStatement(query);
            
            ResultSet rs = buscar.executeQuery();
            
            while(rs.next()){
                PerfilDto dto = new PerfilDto();
                dto.setCodigoPerfil(rs.getInt("codigo_perfil"));
                dto.setNombrePerfil(rs.getString("nombre_perfil"));
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
    
}
