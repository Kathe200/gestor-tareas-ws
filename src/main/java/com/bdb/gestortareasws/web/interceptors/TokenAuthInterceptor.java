package com.bdb.gestortareasws.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.bdb.gestortareasws.utilitarios.Constantes.MSG_ERROR_NO_AUTORIZADO;
import static com.bdb.gestortareasws.utilitarios.Constantes.RES_CODIGO_ERROR_NO_AUTORIZADO;
import static com.bdb.gestortareasws.utilitarios.UtilidadesWeb.sendErrorResponse;

@Component
public class TokenAuthInterceptor implements HandlerInterceptor {
    private static final String PREFIJO_TOKEN = "Bearer ";
    private static final String TOKEN = "miToken123";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith(PREFIJO_TOKEN)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            sendErrorResponse(response, RES_CODIGO_ERROR_NO_AUTORIZADO, MSG_ERROR_NO_AUTORIZADO, false);
            return false;
        }

        String extractedToken = token.substring(PREFIJO_TOKEN.length());

        if (!extractedToken.equals(TOKEN)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            sendErrorResponse(response, RES_CODIGO_ERROR_NO_AUTORIZADO, MSG_ERROR_NO_AUTORIZADO, false);
            return false;
        }

        return true;
    }
}

