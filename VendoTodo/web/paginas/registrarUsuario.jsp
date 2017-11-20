
<%@page import="dto.PerfilDto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <form action="<%= request.getContextPath()%>/RegistrarUsuario" method="POST">
            <table border="0">       
                <tbody>
                    <tr>
                        <td>Login</td>
                        <td><input type="text" name="txtLogin" value="" id="txtLogin" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="txtPass" value="" /></td>
                    </tr>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="txtNombre" value="" id="txtNombre" /></td>
                    </tr>
                    <tr>
                        <td>Apellido</td>
                        <td><input type="text" name="txtApellido" value="" id="txtApellido"/></td>
                    </tr>
                    <tr>
                        <td>Correo</td>
                        <td><input type="text" name="txtCorreo" value="" id="txtCorreo"/></td>
                    </tr>
                    
                    <tr>
                        <td>Fecha Nacimiento</td>
                        <td><input type="date" name="dtFecha" value="" id="dtFecha"/></td>
                    </tr>
                    
                </tbody>
            </table><br>
            <input type="submit" value="Grabar" name="btn" /> 
            <input type="reset" name="reset" value="Limpiar" />
        </form>
             <% String mensaje = (String) request.getAttribute("msg");
            if (mensaje != null) {  %>
            <script>
                alert("<%= mensaje%>");
            </script>
        <% } %>
    </body>
</html>
