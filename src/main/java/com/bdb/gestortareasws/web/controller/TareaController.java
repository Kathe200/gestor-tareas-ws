package com.bdb.gestortareasws.web.controller;

import com.bdb.gestortareasws.domain.dto.RespuestaDTO;
import com.bdb.gestortareasws.domain.dto.TareaDTO;
import com.bdb.gestortareasws.domain.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.bdb.gestortareasws.utilitarios.UtilidadesWeb.transformacionEstado;

@RestController
@RequestMapping("/tasks")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Operation(description = "Método que permite la creación de una tarea", summary = "Crear Tarea")
    @PreAuthorize("hasRole(ROLE_USER)")
    @PostMapping
    public ResponseEntity<RespuestaDTO> crearTarea(@RequestBody TareaDTO tareaDTO) {
        return construirRespuesta(tareaService.crearTarea(tareaDTO));
    }

    @Operation(description = "Método que permite obtener la lista de tareas creadas", summary = "Obtener Tarea")
    @PreAuthorize("hasRole(ROLE_USER)")
    @GetMapping
    public ResponseEntity<RespuestaDTO> obtenerTareas() {
        return construirRespuesta(tareaService.obtenerTareas());
    }

    @Operation(description = "Método que permite actualizar una tarea a partir del id", summary = "Actualizar Tarea")
    @PreAuthorize("hasRole(ROLE_ADMIN)")
    @PutMapping("/{id}")
    public ResponseEntity<RespuestaDTO> actualizarTarea(@PathVariable Integer id, @RequestBody TareaDTO tareaDTO) {
        return construirRespuesta(tareaService.actualizarTarea(id, tareaDTO));
    }

    @Operation(description = "Método que permite eliminar una tarea a partir del id", summary = "Eliminar Tarea")
    @PreAuthorize("hasRole(ROLE_ADMIN)")
    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaDTO> eliminarTarea(@PathVariable Integer id) {
        return construirRespuesta(tareaService.eliminarTarea(id));
    }

    private ResponseEntity<RespuestaDTO> construirRespuesta(RespuestaDTO respuesta) {
        return new ResponseEntity<>(respuesta, transformacionEstado(respuesta));
    }
}
