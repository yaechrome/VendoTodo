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
        <title>Productos</title>
        <link rel="stylesheet" href="../css/style.css" media="all" type="text/css">
        <script type="text/javascript">
            
            window.onload = function () {
                
            };
            function seleccionar(codigo,nombre,precio,tipo){
                txtId = document.getElementById("txtId");
                txtNombre = document.getElementById("txtNombre");
                txtPrecio = document.getElementById("txtPrecio");
                cmbTipo = document.getElementById("cmbTipo");
                txtId.value = codigo;
                txtNombre.value = nombre;
                txtPrecio.value = precio;
                cmbTipo.value = tipo;
            }
        </script>
    </head>
    <body>
       <ul>
            <li><a class="active" href="<%= request.getContextPath()%>/privado/Home">Home</a></li>
            <li style="float:right"><a class="active" href="../paginas/logout.jsp">Salir</a></li>

        </ul>
        <br><br>
        <h1>Productos</h1>
        
        <form action="<%= request.getContextPath()%>/privado/AgregarProducto" method="POST">
            <table border="0">       
                <tbody>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="txtNombre" value="" id="txtNombre"/></td>
                    </tr>
                    <tr>
                        <td>Tipo Producto</td>
                        <td><select name="cmbTipo" id="cmbTipo">
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
                        <td><input type="number" name="txtPrecio" value="" id="txtPrecio"/></td>
                    </tr>
                    <tr>
                        <td>ID Producto</td>
                        <td><input type="text" name="txtId" value="" readonly="true" id="txtId"/></td>
                    </tr>
                </tbody>
            </table><br>
            <table>
                    <tr>
                        <td>
                            <input type="submit" class="log-btn" value="Grabar" name="btn" /> 
                            <input type="submit" class="log-btn" value="Actualizar" name="btn" />    
                            <input type="submit" class="log-btn" value="Mostrar" name="btn" />  
                            <input type="reset" class="log-btn" name="reset" value="Limpiar" />
                        </td>
                    </tr>
                </table>
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
        <form action="<%= request.getContextPath()%>/privado/AgregarProducto" method="POST">
        <table border="1">
            <thead>
                <tr>
                    <th>Codigo Producto</th>
                       <th>Nombre</th>
                       <th>Precio</th>
                       <th>Codigo Tipo</th>
                       <th>Eliminar</th>
                       <th>Seleccionar</th>
                </tr>
            </thead>
            <tbody>
                <% for (ProductoDto dto : lista) {%>
                <tr>
                    <td><%= dto.getCodigoProducto()%></td>
                    <td><%= dto.getNombreProducto()%></td>
                    <td><%= dto.getPrecioProducto()%></td>
                    <td><%= dto.getCodigoTipo()%></td>
                    <td><button  class="log-btn" type="submit" value="<%= dto.getCodigoProducto()%>" name="btnEliminar">Eliminar</button></td>
                    <td><button  class="log-btn" type="button" onclick='seleccionar(<%= dto.getCodigoProducto()%>,"<%= dto.getNombreProducto().replace("\"", "\\\"")%>",<%= dto.getPrecioProducto()%>,<%= dto.getCodigoTipo()%>)'>Seleccionar</button></td>
                    
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
