<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOola</title>
        
        <script type="text/javascript">
	datos = {
		"1": [
			"Leche entera caja 1L",
			"Yogurt frutilla 120g",
			"Platanos 1Kg",
			"Naranjas 1Kg",
			"lechuga escarola 1Un",
			"Zanahorias 1Kg"
		],
		"2": [
			"Nectar durazno 1,5L",
			"Nectar manzana 1,5L",
			"Nectar naranja 1,5L",
			"Bebida 3L",
			"Bebida Zero 3L"
		],
		"3": [
			"TV led 24\"",
			"TV led 32\"",
			"TV led 50\""
		],
		"4": [
			"Tablet 10\" 16GB",
			"Notebook 16\" 500GB",
			"Notebook 19.5\" 1TB"
		],
		"5": [
			"Comedor 4 sillas",
			"Comedor 6 sillas",
			"Sofa 3 cuerpos cafe",
			"Sofa 2 cuerpos Blanco"
		]
	};

	cmbTipos.onchange = function (e) {
		codigoTipo = cmbTipos.value;
	
		productos = datos[cmbTipos.value];
		borrarTodo();
		productos.forEach(function(producto) {
			agregar(producto);
		});
	};

	function agregar(nombre) {
		option = document.createElement("option");
		option.text = nombre;
		cmbProductos.add(option);
	}

	function borrarTodo() {
		while (cmbProductos.length > 0) {
			cmbProductos.remove(0);
		}
	}
</script>

    </head>
    <body>
        <form action="/VendoTodo/Consultas" method="POST">
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
        
        <form action="/VendoTodo/ConsultaVenta" method="POST">
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
