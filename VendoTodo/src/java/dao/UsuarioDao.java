/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UsuarioDto;
import java.util.ArrayList;

/**
 *
 * @author CETECOM
 */
public interface UsuarioDao extends BaseDao<UsuarioDto>{
    public boolean ValidarLogin(String login);
    public boolean ValidarPassword(UsuarioDto usuario, String pass);
    public ArrayList<UsuarioDto> ListarVendedores();
    public String Encriptar(String texto);
    public String Desencriptar(String textoEncriptado);
}
