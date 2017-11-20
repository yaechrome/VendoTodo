

<%@page import="java.util.Map"%>
<%@page import="dao.ProductoDaoImp"%>
<%@page import="dto.DetalleVentaDto"%>
<%@page import="dto.ProductoDto"%>
<%@page import="dto.TipoDto"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="lista" class="dao.ProductoDaoImp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Realizar Venta</title>
        <link rel="stylesheet" href="../css/style.css" media="all" type="text/css">
        <script type="text/javascript">
            
            window.onload = function () {
                listaDetalle =[];
                cmbTipos = document.getElementById("cmbTipos");
                cmbProductos = document.getElementById("cmbProductos");
                txtCantidad = document.getElementById("txtCantidad");
                txtTotal = document.getElementById("txtTotal");
                datos = <%=(String) request.getAttribute("datos")%>;
                btnAgregar = document.getElementById("btnAgregar");
                
                btnAgregar.onclick = function (){
                    
                    agregarDetalle();
                    
                };
                
                
                
                cmbTipos.onchange = function (e) {
                    codigoTipo = cmbTipos.value;

                    productos = datos[cmbTipos.value];
                    borrarTodo();
                    productos.forEach(function(producto) {
                        agregar(producto.nombre, producto.codigo);
                    });
                    
                    calcular();
                };
                
                cmbTipos.onchange();
            };
            function agregar(nombre, codigo) {
                    option = document.createElement("option");
                    option.text = nombre;
                    option.value = codigo;
                    cmbProductos.add(option);
            }

            function borrarTodo() {
                    while (cmbProductos.length > 0) {
                            cmbProductos.remove(0);
                    }
            }
            
            function calcular(){
                producto = datos[cmbTipos.value].find(function(producto) { return producto.codigo == cmbProductos.value });
                txtTotal.value = producto.precio * txtCantidad.value;
            }
            
            function agregarDetalle(){
                
                listaDetalle.push({codigoProducto: cmbProductos.value, cantidad: txtCantidad.value, total: txtTotal.value} );
                
            }
            
        </script>
    </head>
    <body>
        <h1>Realizar Venta</h1>
        <div style="text-align: center;">
        <form action="<%= request.getContextPath()%>/privado/RealizarDetalleVenta?codigo_venta=<%=request.getAttribute("codigo_venta")%>" method="POST">
            <div class="login-form">
            <table border="0">
                
                <tbody>
                    <tr>
                    <td>Tipo: </td>
                    <td><select name="cmbTipos" id="cmbTipos">
                        <% ArrayList<TipoDto> tipos = (ArrayList<TipoDto>) request.getAttribute("tipos");
                        if (tipos != null) {
                            for (TipoDto tipo : tipos) {
                                %>
                                <option value="<%=tipo.getCodigo()%>" > <%=tipo.getNombreTipo()%></option>
                                <%   
                            }
                        }
                        %>
                        </select></td>
                    </tr>
                    <tr>
                        <td>Producto: </td>
                        <td><select name="cmbProductos" id="cmbProductos" onchange="calcular()">
                               
                        </select></td>
                    </tr>
                    <tr>
                        <td>Cantidad: </td>
                        <td><input type="number" name="txtCantidad" value="1" onchange="calcular()" onkeyup="calcular()" id="txtCantidad"/></td>
                    </tr>
                    <tr>
                        <td>Total: </td>
                        <td><input type="text" name="txtTotal" value="" readonly="readonly" id="txtTotal" disabled/></td>
                    </tr>
                </tbody>
            </table>
        </div>
                        <input class="log-btn" type="submit" value="Agregar producto" name="btnAgregarProd" onclick="agregarDetalle()" id="btnAgregar"/>
                
        </form>
         </div>               
        <% String mensaje = (String) request.getAttribute("msg");
            if (mensaje != null) {  %>
            <script>
                alert("<%= mensaje%>");
            </script>
        <% } %>
        
                
    </body>
</html>
