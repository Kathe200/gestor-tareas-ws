package com.bdb.gestortareasws.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Tarea", description = "DTO del objeto Tarea")
public class TareaDTO {

    @Schema(description = "Identificador de la tarea", example = "1")
    private Integer id;
    @Schema(description = "Titulo de la tarea", example = "Tarea inicial")
    private String titulo;
    @Schema(description = "Descripción de la tarea", example = "Descripción de la tarea inicial")
    private String descripcion;
}
