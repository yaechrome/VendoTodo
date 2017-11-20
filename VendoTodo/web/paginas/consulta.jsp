<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta</title>

    </head>
    <body>
        <form action="<%= request.getContextPath()%>/privado/Consultas" method="POST">
            <table border="0">
                <h4>Seleccione la consulta </h4>
                <tbody>
                    <tr>
                        <td><input type="radio" name="consulta" value="Venta" /> Vendedor con más ventas</td>
                        <td><input type="radio" name="consulta" value="Tipo" /> Tipo más vendido</td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Consultar" name="btnConsultar" />
            
            
        </form>
        <c:set var="respuesta" value="${respuesta}" ></c:set>
        <c:out value="${respuesta}" />
        
        <form action="<%= request.getContextPath()%>/privado/ConsultaVenta" method="POST">
            <h5> Ingrese N° de venta: </h5><input type="text" name="txtVenta" value="" />
            <input type="submit" value="Consultar" name="btnConsultarVentas" />
            
        </form>
        
        <c:set var="listaDesplegar" scope="request" 
               value="${listaDesplegar}" ></c:set>
        <c:if test="${listaDesplegar!=null}" >
           <table border="1">
               <thead>
                   <tr>
                       <th>Codigo Producto</th>
                       <th>Cantidad</th>
                       <th>Total</th>
                       <th>Codigo Venta</th>
                   </tr>
               </thead>
               <tbody>
                   <c:forEach var="dto" items="${listaDesplegar}" >
                    <tr>
                       <td><c:out value="${dto.codigoProducto}" /></td>                                       
                       <td><c:out value="${dto.cantidad}" /></td>
                       <td><c:out value="${dto.total}" /></td>      
                       <td><c:out value="${dto.codigoVenta}" /></td>
                    </tr>
                   </c:forEach>
               </tbody>
           </table>

        </c:if>        
        
        <% String mensaje = (String) request.getAttribute("msg");
            if (mensaje != null) {  %>
            <script>
                alert("<%= mensaje%>");
            </script>
        <% } %>
    </body>
</html>
