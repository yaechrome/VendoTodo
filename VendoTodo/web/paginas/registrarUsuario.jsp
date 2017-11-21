
<%@page import="dto.PerfilDto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar usuario</title>
        <link rel="stylesheet" href="../css/style.css" media="all" type="text/css">
    </head>
    <body>
        <section>
            <div class="login-form">
                <form action="<%= request.getContextPath()%>/RegistrarUsuario" method="POST">
                    <h1>Registrar usuario</h1>
                    <table border="0">       
                        <tbody>
                            <tr>
                                <td>Login</td>
                                <td><input type="text" name="txtLogin" value="" id="txtLogin"  /></td>
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
                    <div style="text-align: center;"> 
                        <input type="submit" class="log-btn" value="Grabar" name="btn" /> 
                        <input type="reset" class="log-btn" name="reset" value="Limpiar" />
                        <br>
                        <br>
                        <a  href="<%= request.getContextPath()%>/login.jsp">Iniciar Sesion   </a>
                    </div>


                </form>
            </div>
        </section>
        <% String mensaje = (String) request.getAttribute("msg");
            if (mensaje != null) {%>
        <script>
            alert("<%= mensaje%>");
        </script>
        <% }%>
    </body>
</html>
