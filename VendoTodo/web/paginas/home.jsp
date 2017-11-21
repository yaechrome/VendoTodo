<%-- 
    Document   : home.jsp
    Created on : 19-nov-2017, 18:41:18
    Author     : nippo
--%>

<%@page import="java.util.Date"%>
<%@page import="util.ConstanteUtil"%>
<%@page import="dto.UsuarioDto"%>
<%
    UsuarioDto usuarioDto = (UsuarioDto) request.getSession().getAttribute(ConstanteUtil.LOGIN_USUARIO);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="../css/style.css" media="all" type="text/css">
    </head>
    <body>
        <ul>

            <li style="float:right"><a class="active" href="../paginas/logout.jsp">Salir</a></li>

        </ul>
        <br><br>
        <div class="login-form">
            <h1>Home</h1>

            <fieldset style="display: inline-block;">
                <legend>MENÚ</legend>
                <%if (usuarioDto.getCodigoPerfil() == 1) {%>
                <a href="<%= request.getContextPath()%>/privado/AgregarUsuario">Mantenedor de Usuarios     </a>
                <br>
                <%}%>

                <%if (usuarioDto.getCodigoPerfil() == 1) {%>
                <a href="<%= request.getContextPath()%>/privado/AgregarProducto">Mantenedor de Productos   </a>
                <br>
                <%}%>

                <%if (usuarioDto.getCodigoPerfil() == 1 || usuarioDto.getCodigoPerfil() == 3) {%>
                <a href="<%= request.getContextPath()%>/privado/RealizarVenta">Realizar Venta     </a>
                <br>
                <%}%>

                <%if (usuarioDto.getCodigoPerfil() == 1 || usuarioDto.getCodigoPerfil() == 2) {%>
                <a href="<%= request.getContextPath()%>/privado/Consultas">Consultas     </a>
                <br>
                <%}%>
            </fieldset>
            <br>
        </div>

        <ul>
            <li><a class="active"> Usuario conectado: <%=usuarioDto.getNombreUsuario()%></a></li>
        </ul>

           



    </body>
</html>
