/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import dto.UsuarioDto;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import servlet.Login;

/**
 *
 * @author nippo
 */
@WebFilter(filterName = "SessionFilter", urlPatterns = {"/privado/*"})
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        UsuarioDto usuarioAutenticado = (UsuarioDto) session.getAttribute(Login.LOGIN_USUARIO);

        if (usuarioAutenticado == null) {
            System.err.println("La sesión no registra ningún usuario autenticado");
            System.err.println("Se redirecciona usuario a página de login");
            request.getRequestDispatcher(Login.LOGIN_URL_FILE).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // ninguna configuración que realizar al inicio
    }

    @Override
    public void destroy() {
        // ninguna acción que realizar al finalizar
    }
}
