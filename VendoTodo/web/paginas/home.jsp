<%-- 
    Document   : home.jsp
    Created on : 19-nov-2017, 18:41:18
    Author     : nippo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <fieldset style="display: inline-block;">
            <legend>Agregar:</legend>
            <a href="<%= request.getContextPath()%>/privado/AgregarUsuario">Usuario     </a>
            <br>
            <a href="<%= request.getContextPath()%>/privado/AgregarProducto">Producto   </a>
            <br>
            <a href="<%= request.getContextPath()%>/privado/RealizarDetalleVenta">RealizarVenta     </a>
            <br>
            <a href="<%= request.getContextPath()%>/privado/Consultas">Consultas     </a>
        </fieldset>
        <br>
        <br>
        <a href="../paginas/logout.jsp">Salir</a>
    </body>
</html>
