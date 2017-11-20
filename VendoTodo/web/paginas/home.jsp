<%-- 
    Document   : home.jsp
    Created on : 19-nov-2017, 18:41:18
    Author     : nippo
--%>

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
    </head>
    <body>
        <fieldset style="display: inline-block;">
            <legend>Agregar:</legend>
            <%if (usuarioDto.getCodigoPerfil() == 1) {%>
            <a href="<%= request.getContextPath()%>/privado/AgregarUsuario">Usuario     </a>
             <br>
            <%}%>
           
            <%if (usuarioDto.getCodigoPerfil() == 1) {%>
            <a href="<%= request.getContextPath()%>/privado/AgregarProducto">Producto   </a>
             <br>
            <%}%>
           
            <%if (usuarioDto.getCodigoPerfil() == 1 || usuarioDto.getCodigoPerfil() == 3) {%>
            <a href="<%= request.getContextPath()%>/privado/RealizarDetalleVenta">RealizarVenta     </a>
             <br>
            <%}%>
           
            <%if (usuarioDto.getCodigoPerfil() == 1 || usuarioDto.getCodigoPerfil() == 2) {%>
            <a href="<%= request.getContextPath()%>/privado/Consultas">Consultas     </a>
            <%}%>
        </fieldset>
        <br>
        <br>
        <a href="../paginas/logout.jsp">Salir</a>
    </body>
</html>
