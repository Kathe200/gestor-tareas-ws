package com.bdb.gestortareasws.persistence.mapper;

import com.bdb.gestortareasws.domain.dto.TareaDTO;
import com.bdb.gestortareasws.persistence.entity.Tarea;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TareaMapper {
   TareaDTO toDTO(Tarea tarea);
   Tarea toEntity(TareaDTO tareaDTO);
}
