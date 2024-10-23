package com.bdb.gestortareasws.domain.service;

import com.bdb.gestortareasws.domain.dto.RespuestaDTO;
import com.bdb.gestortareasws.domain.dto.TareaDTO;
import com.bdb.gestortareasws.persistence.entity.Tarea;
import com.bdb.gestortareasws.persistence.mapper.TareaMapper;
import com.bdb.gestortareasws.persistence.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.bdb.gestortareasws.utilitarios.Constantes.*;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private TareaMapper tareaMapper;

    public RespuestaDTO crearTarea(TareaDTO tareaDTO) {

        RespuestaDTO<TareaDTO> respuestaDTO = new RespuestaDTO<>();

        try {
            if (tareaDTO == null) {
                respuestaDTO.setCodigo(RES_CODIGO_ERROR_DATOS_INCOMPLETOS);
                respuestaDTO.setDescripcion(MSG_ERROR_SIN_DATOS_ENTRADA);
                respuestaDTO.setEsExitoso(false);
                return respuestaDTO;
            }

            Tarea tarea = tareaMapper.toEntity(tareaDTO);

            tarea = tareaRepository.save(tarea);

            respuestaDTO.setCodigo(RES_CODIGO_EXITOSO);
            respuestaDTO.setDescripcion(MSG_EXITOSO);
            respuestaDTO.setEsExitoso(true);
            respuestaDTO.setData(tareaMapper.toDTO(tarea));

        } catch (Exception ex) {
            respuestaDTO.setCodigo(RES_CODIGO_ERROR_GENERICO);
            respuestaDTO.setDescripcion(MSG_ERROR_NO_MANEJADO + "Error inesperado al crear la tarea: " + ex.getMessage());
            respuestaDTO.setEsExitoso(false);
        }
        return respuestaDTO;
    }

    public RespuestaDTO obtenerTareas() {

        RespuestaDTO<List<TareaDTO>> respuestaDTO = new RespuestaDTO<>();

        try {
            List<TareaDTO> tareas = tareaRepository.findAll().stream()
                    .map(tareaMapper::toDTO)
                    .collect(Collectors.toList());

            if (tareas.isEmpty()) {
                respuestaDTO.setCodigo(RES_CODIGO_ERROR_SIN_DATOS);
                respuestaDTO.setDescripcion(MSG_ERROR_SIN_DATOS);
                respuestaDTO.setEsExitoso(false);
                return respuestaDTO;
            }

            respuestaDTO.setCodigo(RES_CODIGO_EXITOSO);
            respuestaDTO.setDescripcion(MSG_EXITOSO);
            respuestaDTO.setEsExitoso(true);
            respuestaDTO.setData(tareas);

        } catch (Exception ex) {
            respuestaDTO.setCodigo(RES_CODIGO_ERROR_GENERICO);
            respuestaDTO.setDescripcion(MSG_ERROR_NO_MANEJADO + "Error al obtener las tareas: " + ex.getMessage());
            respuestaDTO.setEsExitoso(false);
        }
        return respuestaDTO;
    }

    public RespuestaDTO actualizarTarea(Integer id, TareaDTO tareaDTO) {

        RespuestaDTO<TareaDTO> respuestaDTO = new RespuestaDTO<>();

        try {
            Tarea tareaExistente = tareaRepository.findById(id).orElse(null);

            if (tareaExistente == null) {
                respuestaDTO.setCodigo(RES_CODIGO_ERROR_SIN_DATOS);
                respuestaDTO.setDescripcion("Tarea con ID " + id + " no encontrada");
                respuestaDTO.setEsExitoso(false);
                return respuestaDTO;
            }

            tareaExistente.setTitulo(tareaDTO.getTitulo());
            tareaExistente.setDescripcion(tareaDTO.getDescripcion());

            Tarea tareaActualizada = tareaRepository.save(tareaExistente);

            respuestaDTO.setCodigo(RES_CODIGO_EXITOSO);
            respuestaDTO.setDescripcion(MSG_EXITOSO);
            respuestaDTO.setEsExitoso(true);
            respuestaDTO.setData(tareaMapper.toDTO(tareaActualizada));

        } catch (Exception ex) {
            respuestaDTO.setCodigo(RES_CODIGO_ERROR_GENERICO);
            respuestaDTO.setDescripcion(MSG_ERROR_NO_MANEJADO + "Error al actualizar la tarea: " + ex.getMessage());
            respuestaDTO.setEsExitoso(false);
        }

        return respuestaDTO;
    }

    public RespuestaDTO<Void> eliminarTarea(Integer id) {

        RespuestaDTO<Void> respuestaDTO = new RespuestaDTO<>();

        try {
            if (!tareaRepository.existsById(id)) {
                respuestaDTO.setCodigo(RES_CODIGO_ERROR_SIN_DATOS);
                respuestaDTO.setDescripcion("Tarea con ID " + id + " no encontrada");
                respuestaDTO.setEsExitoso(false);
                return respuestaDTO;
            }

            tareaRepository.deleteById(id);

            respuestaDTO.setCodigo(RES_CODIGO_EXITOSO);
            respuestaDTO.setDescripcion(MSG_EXITOSO);
            respuestaDTO.setEsExitoso(true);

        } catch (Exception ex) {
            respuestaDTO.setCodigo(RES_CODIGO_ERROR_GENERICO);
            respuestaDTO.setDescripcion(MSG_ERROR_NO_MANEJADO + "Error al eliminar la tarea: " + ex.getMessage());
            respuestaDTO.setEsExitoso(false);
        }

        return respuestaDTO;
    }
}

