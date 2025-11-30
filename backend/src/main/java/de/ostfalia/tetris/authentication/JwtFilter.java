package de.ostfalia.tetris.authentication;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import de.ostfalia.tetris.player.Player;
import de.ostfalia.tetris.player.PlayerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final PlayerService playerService;

    public JwtFilter(JwtService jwtService, PlayerService playerService) {
        this.jwtService = jwtService;
        this.playerService = playerService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Diese Endpunkte dürfen NIEMALS gefiltert werden
        if (path.startsWith("/authentication")
                || path.startsWith("/player")
                || path.startsWith("/swagger")
                || path.startsWith("/v3")
                || path.startsWith("/h2-console")) {

            filterChain.doFilter(request, response);
            return;
        }

        // Token lesen
        String auth = request.getHeader("Authorization");
        if (auth == null || !auth.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = auth.substring(7);

        if (!jwtService.isValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtService.extractUsername(token);
        Player user = playerService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}

