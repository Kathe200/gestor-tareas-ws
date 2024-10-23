package com.bdb.gestortareasws.web.filters;

import com.bdb.gestortareasws.domain.dto.RespuestaDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bdb.gestortareasws.utilitarios.Constantes.MSG_ERROR_NO_AUTORIZADO;
import static com.bdb.gestortareasws.utilitarios.Constantes.RES_CODIGO_ERROR_NO_AUTORIZADO;
import static com.bdb.gestortareasws.utilitarios.UtilidadesWeb.sendErrorResponse;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private static final String PREFIJO_TOKEN = "Bearer ";
    private static final String TOKEN = "miToken123";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith(PREFIJO_TOKEN)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            sendErrorResponse(response, RES_CODIGO_ERROR_NO_AUTORIZADO, MSG_ERROR_NO_AUTORIZADO, false);
            return;
        }

        String extractedToken = token.substring(7);

        if (!extractedToken.equals(TOKEN)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            sendErrorResponse(response, RES_CODIGO_ERROR_NO_AUTORIZADO, MSG_ERROR_NO_AUTORIZADO, false);
            return;
        }

        filterChain.doFilter(request, response);
    }
}


