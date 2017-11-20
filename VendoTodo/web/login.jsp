<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Demo Autenticación</title>
    </head>
    <body>
        <header>
            <h1>Demo Autenticación</h1>
        </header>

        <section>
            <h2>Login</h2>          
            <form action="<%= request.getContextPath()%>/Login" method="POST">
                <fieldset>
                    <legend>Credenciales de usuario</legend> 

                    <% if (request.getAttribute("mensajeError") != null) {%>
                    <div class="error">
                        <p>             
                            <%= request.getAttribute("mensajeError")%>
                        </p>
                    </div>
                    <% }%>
                    <table>
                        <tr>
                            <td>Nombre: </td>
                            <td>
                                <input type="text" name="user" required/>
                            </td>
                        </tr>
                        <tr>
                            <td>Clave: </td>
                            <td>
                                <input type="password" name="pass" required/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" name="submit" value="Ingresar" />
                                <input type="reset" name="reset" value="Limpiar" />
                            </td>                        
                        </tr>
                    </table>
                </fieldset>
            </form>
        </section>

        <footer>
            Desarrollo Web en Java
        </footer>
    </body>
</html>
