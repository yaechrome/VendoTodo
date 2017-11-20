<%-- 
    Document   : agregarUsuario
    Created on : 12-nov-2017, 19:45:19
    Author     : yaechrome
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.PerfilDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Agregar Usuario</h1>
        
        <form action="<%= request.getContextPath()%>/AgregarUsuario" method="POST">
            <table border="0">       
                <tbody>
                    <tr>
                        <td>Login</td>
                        <td><input type="text" name="txtLogin" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="text" name="txtPass" value="" /></td>
                    </tr>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="txtNombre" value="" /></td>
                    </tr>
                    <tr>
                        <td>Apellido</td>
                        <td><input type="text" name="txtApellido" value="" /></td>
                    </tr>
                    <tr>
                        <td>Correo</td>
                        <td><input type="text" name="txtCorreo" value="" /></td>
                    </tr>
                    <tr>
                        <td>Perfil</td>
                        <td><select name="cmbPerfil">
                        <% ArrayList<PerfilDto> perfiles = (ArrayList<PerfilDto>) request.getAttribute("perfiles");
                        if (perfiles != null) {
                            for (PerfilDto perfil : perfiles) {
                                %>
                                    <option value="<%=perfil.getCodigoPerfil()%>"> <%=perfil.getNombrePerfil() %></option>
                                <%   
                            }
                        }
                        %>
                        </select></td>
                    </tr>
                    <tr>
                        <td>Fecha Nacimiento</td>
                        <td><input type="date" name="dtFecha" value="" /></td>
                    </tr>
                </tbody>
            </table><br>
            <input type="submit" value="GRABAR" name="btnGrabar" />            
        </form>               
         <% String mensaje = (String) request.getAttribute("msg");
            if (mensaje != null) {  %>
            <script>
                alert("<%= mensaje%>");
            </script>
        <% } %>
        
    </body>
</html>
