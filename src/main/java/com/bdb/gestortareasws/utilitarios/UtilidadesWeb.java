package com.bdb.gestortareasws.utilitarios;

import com.bdb.gestortareasws.domain.dto.RespuestaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bdb.gestortareasws.utilitarios.Constantes.*;

public class UtilidadesWeb {

    private UtilidadesWeb(){}

    public static HttpStatus transformacionEstado(RespuestaDTO respuesta){

        switch (respuesta.getCodigo()) {
            case RES_CODIGO_EXITOSO:
                return HttpStatus.OK;
            case RES_CODIGO_ERROR_GENERICO:
                return HttpStatus.INTERNAL_SERVER_ERROR;
            case RES_CODIGO_ERROR_SIN_DATOS:
                return HttpStatus.NOT_FOUND;
            case RES_CODIGO_ERROR_DATOS_INCOMPLETOS:
                return HttpStatus.BAD_REQUEST;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public static void sendErrorResponse(HttpServletResponse response, String codigo, String descripcion, boolean esExitoso) throws IOException {
        RespuestaDTO<Void> respuestaError = new RespuestaDTO<>(codigo, descripcion, esExitoso, null);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(respuestaError));
    }

}
