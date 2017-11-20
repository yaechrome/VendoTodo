<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/style.css" media="all" type="text/css">
    </head>
    <body>
        <header>
            <h1>Autenticación</h1>
        </header>

        <section>
            <div class="login-form">
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

                        <div class="form-group ">
                            <input class="form-control" type="text" name="user" required placeholder="Usuario"/>
                            <i class="fa fa-user"></i>
                        </div>


                        <div class="form-group ">
                            <input class="form-control" type="password" name="pass" required placeholder="Password"/>
                            <i class="fa fa-lock"></i>
                        </div>

                        <input class="log-btn" type="submit" name="submit" value="Ingresar" />
                        <input class="log-btn" type="reset" name="reset" value="Limpiar" />

                    </fieldset>                   
                </form>
            </div>

        </section>

    </body>
</html>
