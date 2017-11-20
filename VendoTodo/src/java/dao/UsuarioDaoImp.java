package dao;

import dto.UsuarioDto;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sql.Conexion;

public class UsuarioDaoImp implements UsuarioDao {

    @Override
    public boolean ValidarLogin(String login) {
        try {
            Connection conexion = Conexion.getConexion();
            String query = "select * from usuarios where login_usuario = ?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setString(1, login);
            buscar.execute();
            ResultSet rs = buscar.executeQuery();
            if (rs.next()) {
                return true;
            }
            buscar.close();
            conexion.close();

        } catch (SQLException w) {
            System.out.println("Error  " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return false;
    }

    

    @Override
    public boolean ValidarPassword(String login, String pass) {
        try {
            Connection conexion = Conexion.getConexion();
            String query = "select pass_usuario from usuarios where login_usuario = ?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setString(1, login);
            buscar.execute();

            try (ResultSet rs = buscar.executeQuery()) {
                if (rs.next()) {
                    if (rs.getString("pass_usuario").equals(pass)) {

                        return true;
                    }
                }
            }
            buscar.close();
            conexion.close();

        } catch (SQLException w) {
            System.out.println("Error  " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean agregar(UsuarioDto dto) {
        try {

            Connection conexion = Conexion.getConexion();

            String query = "INSERT INTO usuarios ( login_usuario, pass_usuario, nombre_usuario, apellido_usuario, correo_usuario, codigo_perfil, fechaNacimiento_usuario) VALUES ( ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement insertar = conexion.prepareStatement(query);

            insertar.setString(1, dto.getLoginUsuario());
            insertar.setString(2, dto.getPassUsuario());
            insertar.setString(3, dto.getNombreUsuario());
            insertar.setString(4, dto.getApellidoUsuario());
            insertar.setString(5, dto.getCorreoUsuario());
            insertar.setInt(6, dto.getCodigoPerfil());
            insertar.setDate(7, new java.sql.Date(dto.getFechaNacimiento().getTime()));
            insertar.execute();
            insertar.close();
            conexion.close();
            return true;
        } catch (SQLException w) {
            System.out.println("Error SQL al agregar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al agregar " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminar(int codigo) {
        try {

            Connection conexion = Conexion.getConexion();
            String query1 = "SET FOREIGN_KEY_CHECKS=0";
            String query2 = "DELETE FROM usuarios WHERE id_usuario=?";
            String query3 = "SET FOREIGN_KEY_CHECKS=1";
            PreparedStatement eliminarFK = conexion.prepareStatement(query1);
            PreparedStatement eliminar = conexion.prepareStatement(query2);
            PreparedStatement agregarFK = conexion.prepareStatement(query3);

            eliminarFK.execute();
            eliminarFK.close();

            eliminar.setInt(1, codigo);
            eliminar.execute();
            eliminar.close();

            agregarFK.execute();
            agregarFK.close();

            conexion.close();
            return true;
        } catch (SQLException w) {
            System.out.println("Error SQL al eliminar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modificar(UsuarioDto dto) {
        try {

            Connection conexion = Conexion.getConexion();

            String query = "update Usuarios set login_usuario=?, pass_usuario=?, nombre_usuario=?, apellido_usuario=?, correo_usuario=?, codigo_perfil=?, fechaNacimiento_usuario=? where id_usuario=?";

            PreparedStatement modificar = conexion.prepareStatement(query);

            modificar.setString(1, dto.getLoginUsuario());
            modificar.setString(2, dto.getPassUsuario());
            modificar.setString(3, dto.getNombreUsuario());
            modificar.setString(4, dto.getApellidoUsuario());
            modificar.setString(5, dto.getCorreoUsuario());
            modificar.setInt(6, dto.getCodigoPerfil());
            modificar.setDate(7, new java.sql.Date(dto.getFechaNacimiento().getTime()));
            modificar.setInt(8, dto.getIdUsuario());
            modificar.execute();
            modificar.close();
            conexion.close();
            return true;
        } catch (SQLException w) {
            System.out.println("Error SQL al modificar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al modificar " + e.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList<UsuarioDto> listar() {
        ArrayList<UsuarioDto> lista = new ArrayList<UsuarioDto>();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM usuarios";
            PreparedStatement buscar = conexion.prepareStatement(query);

            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                UsuarioDto dto = new UsuarioDto();
                dto.setIdUsuario(rs.getInt("id_usuario"));
                dto.setLoginUsuario(rs.getString("login_usuario"));
                dto.setPassUsuario(rs.getString("pass_usuario"));
                dto.setNombreUsuario(rs.getString("nombre_usuario"));
                dto.setApellidoUsuario(rs.getString("apellido_usuario"));
                dto.setCorreoUsuario(rs.getString("correo_usuario"));
                dto.setCodigoPerfil(rs.getInt("codigo_perfil"));
                dto.setFechaNacimiento(rs.getDate("fechaNacimiento_usuario"));
                lista.add(dto);
            }
            buscar.close();
            conexion.close();
        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return lista;
    }

    @Override
    public ArrayList<UsuarioDto> ListarVendedores() {
        ArrayList<UsuarioDto> lista = new ArrayList<UsuarioDto>();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM usuarios where codigo_perfil = 1 or codigo_perfil= 3";
            PreparedStatement buscar = conexion.prepareStatement(query);

            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                UsuarioDto dto = new UsuarioDto();
                dto.setIdUsuario(rs.getInt("id_usuario"));
                dto.setLoginUsuario(rs.getString("login_usuario"));
                dto.setPassUsuario(rs.getString("pass_usuario"));
                dto.setNombreUsuario(rs.getString("nombre_usuario"));
                dto.setApellidoUsuario(rs.getString("apellido_usuario"));
                dto.setCorreoUsuario(rs.getString("correo_usuario"));
                dto.setCodigoPerfil(rs.getInt("codigo_perfil"));
                lista.add(dto);
            }
            buscar.close();
            conexion.close();
        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return lista;
    }

    @Override
    public String Encriptar(String texto) {
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;

    }

    @Override
    public String Desencriptar(String textoEncriptado) {
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    @Override
    public UsuarioDto BuscarUsuario(String login) {
        UsuarioDto dto = new UsuarioDto();
        try {
            Connection conexion = Conexion.getConexion();
            String query = "SELECT * FROM usuarios where login_usuario = ?";
            PreparedStatement buscar = conexion.prepareStatement(query);
            buscar.setString(1, login);
            ResultSet rs = buscar.executeQuery();

            if(rs.next()) {
                
                dto.setIdUsuario(rs.getInt("id_usuario"));
                dto.setLoginUsuario(rs.getString("login_usuario"));
                dto.setPassUsuario(rs.getString("pass_usuario"));
                dto.setNombreUsuario(rs.getString("nombre_usuario"));
                dto.setApellidoUsuario(rs.getString("apellido_usuario"));
                dto.setCorreoUsuario(rs.getString("correo_usuario"));
                dto.setCodigoPerfil(rs.getInt("codigo_perfil"));
                
            }
            buscar.close();
            conexion.close();
           
        } catch (SQLException w) {
            System.out.println("Error SQL al buscar " + w.getMessage());
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return dto;
    }

}
