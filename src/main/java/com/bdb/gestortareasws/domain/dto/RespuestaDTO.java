package com.bdb.gestortareasws.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.bdb.gestortareasws.utilitarios.Constantes.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Respuesta", description = "DTO de la respuesta del consumo")
public class RespuestaDTO<T> {

    @Schema(description = DESC_RESPUESTA_CODIGO, example = "0", allowableValues = {RES_CODIGO_EXITOSO, RES_CODIGO_ERROR_GENERICO, RES_CODIGO_ERROR_SIN_DATOS, RES_CODIGO_ERROR_DATOS_INCOMPLETOS})
    private String codigo;

    @Schema(description = "Descripción de la respuesta, en caso de error mostrara el mensaje del error", example = "Exitoso")
    private String descripcion;

    @Schema(description = "Si es exitoso el consumo", example = "true")
    private boolean esExitoso;

    @Schema(description = "Objeto de respuesta opcional")
    private T data;
}
