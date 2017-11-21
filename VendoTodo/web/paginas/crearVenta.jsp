<%-- 
    Document   : crearVenta
    Created on : 20-nov-2017, 20:56:58
    Author     : yaechrome
--%>
<%@page import="util.ConstanteUtil"%>
<%@page import="dto.UsuarioDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Venta</title>
        <link rel="stylesheet" href="../css/style.css" media="all" type="text/css">
        <%
            Integer codigoVenta = (Integer) request.getAttribute("codigo_venta");
            if (codigoVenta != null) {%>
        <meta http-equiv="refresh" content="0; url=<%= request.getContextPath()%>/privado/RealizarDetalleVenta?codigo_venta=<%=codigoVenta%>" />
        <%}
        %>
    </head>
    <body>
        <h1>Crear Venta</h1>
        <form action="<%= request.getContextPath()%>/privado/RealizarVenta" method="POST">
            <table>
                <tr>
                    <td>
                        <input  class="log-btn"  type="submit" value="Crear" name="btnCrear" />
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>
