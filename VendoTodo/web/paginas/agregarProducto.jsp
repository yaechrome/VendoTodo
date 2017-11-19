<%-- 
    Document   : agregarProducto
    Created on : 13-nov-2017, 19:38:05
    Author     : yaechrome
--%>

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
