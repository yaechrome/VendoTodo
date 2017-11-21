
<%@page import="dto.UsuarioDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.PerfilDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
        <link rel="stylesheet" href="../css/style.css" media="all" type="text/css">
        <script type="text/javascript">

            window.onload = function () {

            };
            function seleccionar(id, login, nombre, apellido, correo, fecha, perfil) {
                txtId = document.getElementById("txtId");
                txtLogin = document.getElementById("txtLogin");
                txtNombre = document.getElementById("txtNombre");
                txtApellido = document.getElementById("txtApellido");
                txtCorreo = document.getElementById("txtCorreo");
                cmbPerfil = document.getElementById("cmbPerfil");
                dtFecha = document.getElementById("dtFecha");

                txtId.value = id;
                txtLogin.value = login;
                txtNombre.value = nombre;
                txtApellido.value = apellido;
                txtCorreo.value = correo;
                cmbPerfil.value = perfil;
                dtFecha.value = fecha;

            }
        </script>
    </head>
    <body>
        <ul>
            <li><a class="active" href="<%= request.getContextPath()%>/privado/Home">Home</a></li>
            <li><a href="../paginas/logout.jsp">Salir</a></li>
            
        </ul>
        <br>
        <h1>Usuarios</h1>

        <form action="<%= request.getContextPath()%>/privado/AgregarUsuario" method="POST">
            <div style="login-form">

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
                            <td>Perfil</td>
                            <td><select name="cmbPerfil" id="cmbPerfil">
                                    <% ArrayList<PerfilDto> perfiles = (ArrayList<PerfilDto>) request.getAttribute("perfiles");
                                        if (perfiles != null) {
                                            for (PerfilDto perfil : perfiles) {
                                    %>
                                    <option value="<%=perfil.getCodigoPerfil()%>"> <%=perfil.getNombrePerfil()%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select></td>
                        </tr>
                        <tr>
                            <td>Fecha Nacimiento</td>
                            <td><input type="date" name="dtFecha" value="" id="dtFecha"/></td>
                        </tr>
                        <tr>
                            <td>ID</td>
                            <td><input type="text" name="txtId" value="" readonly="true" id="txtId"/></td>
                        </tr>
                    </tbody>
                </table><br>
                <input type="submit" class="log-btn" value="Grabar" name="btn" /> 
                <input type="submit" class="log-btn" value="Actualizar" name="btn" />    
                <input type="submit" class="log-btn" value="Mostrar" name="btn" />  
                <input type="reset" class="log-btn" name="reset" value="Limpiar" />
            </div>

        </form>



        <% ArrayList<UsuarioDto> lista
                    = (ArrayList<UsuarioDto>) request.getAttribute("lista");
            if (lista != null) {
                if (lista.size() == 0) {%>
        <script>
            alert("NO hay registros");
        </script>
        <% } else {%>
        <br>
        <form action="<%= request.getContextPath()%>/privado/AgregarUsuario" method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Login</th>

                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Correo</th>
                        <th>Fecha Nacimiento</th>
                        <th>Codigo Perfil</th>
                        <th>Eliminar</th>
                        <th>Seleccionar</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (UsuarioDto dto : lista) {%>
                    <tr>
                        <td><%= dto.getIdUsuario()%></td>
                        <td><%= dto.getLoginUsuario()%></td>

                        <td><%= dto.getNombreUsuario()%></td>
                        <td><%= dto.getApellidoUsuario()%></td>
                        <td><%= dto.getCorreoUsuario()%></td>
                        <td><%= dto.getFechaNacimiento()%></td>
                        <td><%= dto.getCodigoPerfil()%></td>
                        <td><button type="submit" value="<%= dto.getIdUsuario()%>" name="btnEliminar">Eliminar</button></td>
                        <td><button type="button" onclick='seleccionar(<%=dto.getIdUsuario()%>, "<%=dto.getLoginUsuario().replace("\"", "\\\"")%>",
                                    "<%=dto.getNombreUsuario()%>", "<%=dto.getApellidoUsuario()%>", "<%=dto.getCorreoUsuario().replace("\"", "\\\"")%>",
                                    "<%=dto.getFechaNacimiento().toString()%>",<%=dto.getCodigoPerfil()%>)'>Seleccionar</button></td>

                    </tr>
                    <% } %>  
                </tbody>
            </table>
        </form> 
        <% } %>
        <% }%>               

        <% String mensaje = (String) request.getAttribute("msg");
             if (mensaje != null) {%>
        <script>
                alert("<%= mensaje%>");
        </script>
        <% }%>

    </body>
</html>
