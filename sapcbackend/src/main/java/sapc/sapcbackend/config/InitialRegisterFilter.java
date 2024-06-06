package sapc.sapcbackend.config;

import sapc.sapcbackend.db.entities.UserRole;
import sapc.sapcbackend.db.entities.Usuarios;
import sapc.sapcbackend.db.repositories.UsuarioRepositoryCustom;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class InitialRegisterFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepositoryCustom usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if ("/auth/initialRegister".equals(request.getRequestURI()) && request.getMethod().equalsIgnoreCase("POST")) {
            long adminCount = usuarioRepository.countByRole("0");  // "0" for ADMIN
            Optional<Usuarios> adminUser = usuarioRepository.findByLogin("ADMIN");

            if (!(adminCount == 1 && adminUser.isPresent() && adminUser.get().isActive())) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
