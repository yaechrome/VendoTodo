<%-- 
    Document   : agregarProducto
    Created on : 13-nov-2017, 19:38:05
    Author     : yaechrome
--%>
<%@page import="dto.ProductoDto"%>

<%@page import="dto.TipoDto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Agregar Producto</h1>
        
        <form action="/VendoTodo/AgregarProducto" method="POST">
            <table border="0">       
                <tbody>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="txtNombre" value="" /></td>
                    </tr>
                    <tr>
                        <td>Tipo Producto</td>
                        <td><select name="cmbTipo">
                        <% ArrayList<TipoDto> tipos = (ArrayList<TipoDto>) request.getAttribute("tipos");
                        if (tipos != null) {
                            for (TipoDto tipo : tipos) {
                                %>
                                    <option value="<%=tipo.getCodigo() %>"> <%=tipo.getNombreTipo() %></option>
                                <%   
                            }
                        }
                        %>
                        </select></td>
                    </tr>
                    <tr>
                        <td>Precio</td>
                        <td><input type="text" name="txtPrecio" value="" /></td>
                    </tr>
                    <tr>
                        <td>ID</td>
                        <td><input type="text" name="txtId" value="" disabled = "true"/></td>
                    </tr>
                </tbody>
            </table><br>
            <input type="submit" value="Grabar" name="btn" /> 
            <input type="submit" value="Eliminar" name="btn" />  
            <input type="submit" value="Actualizar" name="btn" />    
            <input type="submit" value="Mostrar" name="btn" />     
        </form> 

           <% ArrayList<ProductoDto> lista
                    = (ArrayList<ProductoDto>) request.getAttribute("lista");
            if (lista != null) {
        if (lista.size() == 0) {%>
        <script>
            alert("NO hay registros");
        </script>
        <% } else {  %>
        <br>
        <form action="/VendoTodo/AgregarProducto" method="POST">
        <table border="1">
            <thead>
                <tr>
                    <th>Codigo Producto</th>
                       <th>Nombre</th>
                       <th>Precio</th>
                       <th>Codigo Tipo</th>
                       <th>Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <% for (ProductoDto dto : lista) {%>
                <tr>
                    <td><%= dto.getCodigoProducto()%></td>
                    <td><%= dto.getNombreProducto()%></td>
                    <td><%= dto.getPrecioProducto()%></td>
                    <td><%= dto.getCodigoTipo()%></td>
                    <td><button type="submit" value="<%= dto.getCodigoProducto()%>" name="btnEliminar">Eliminar</button></td>
                </tr>
                <% } %>  
            </tbody>
        </table>
        </form> 
        <% } %>
        <% }%>

    
         <% String mensaje = (String) request.getAttribute("msg");
            if (mensaje != null) {  %>
            <script>
                alert("<%= mensaje%>");
            </script>
        <% } %>
    </body>
</html>
