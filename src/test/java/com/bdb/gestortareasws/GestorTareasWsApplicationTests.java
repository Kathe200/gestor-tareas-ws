package com.bdb.gestortareasws;

import com.bdb.gestortareasws.domain.dto.RespuestaDTO;
import com.bdb.gestortareasws.domain.dto.TareaDTO;
import com.bdb.gestortareasws.domain.service.TareaService;
import com.bdb.gestortareasws.web.controller.TareaController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.bdb.gestortareasws.utilitarios.Constantes.MSG_EXITOSO;
import static com.bdb.gestortareasws.utilitarios.Constantes.RES_CODIGO_EXITOSO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class GestorTareasWsApplicationTests {

    @Mock
    private TareaService tareaService;

    @InjectMocks
    private TareaController tareaController;

    private TareaDTO tareaDTO;

    private RespuestaDTO respuestaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        tareaDTO = new TareaDTO();
        tareaDTO.setTitulo("Mi primera tarea");
        tareaDTO.setDescripcion("Descripcion 1");

        // Inicializa la respuesta esperada
        respuestaDTO = new RespuestaDTO();
        respuestaDTO.setCodigo(RES_CODIGO_EXITOSO);
        respuestaDTO.setDescripcion(MSG_EXITOSO);
        respuestaDTO.setEsExitoso(true);

        // Configura la data que se espera en la respuesta
        TareaDTO data = new TareaDTO();
        data.setId(3); // Supongamos que el ID devuelto es 3
        data.setTitulo("Mi primera tarea");
        data.setDescripcion("Descripcion 1");

        respuestaDTO.setData(data);
    }

    @Test
    public void testCrearTarea() {
        when(tareaService.crearTarea(any(TareaDTO.class))).thenReturn(respuestaDTO);

        ResponseEntity<RespuestaDTO> respuesta = tareaController.crearTarea(tareaDTO);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals(RES_CODIGO_EXITOSO, respuesta.getBody().getCodigo());
        assertEquals(MSG_EXITOSO, respuesta.getBody().getDescripcion());
        assertTrue(respuesta.getBody().isEsExitoso());

        TareaDTO tareaCreada = (TareaDTO) respuesta.getBody().getData();

        assertNotNull(tareaCreada);
        assertEquals(3, tareaCreada.getId());
        assertEquals("Mi primera tarea", tareaCreada.getTitulo());
        assertEquals("Descripcion 1", tareaCreada.getDescripcion());

        verify(tareaService, times(1)).crearTarea(tareaDTO);
    }
    @Test
    void testObtenerTareas() {

        List<TareaDTO> tareasSimuladas = new ArrayList<>();
        TareaDTO tarea1 = new TareaDTO();
        tarea1.setId(1);
        tarea1.setTitulo("Tarea inicial");
        tarea1.setDescripcion("Descripción de la tarea inicial");

        tareasSimuladas.add(tarea1);

        RespuestaDTO<List<TareaDTO>> respuestaDTO = new RespuestaDTO<>();
        respuestaDTO.setCodigo(RES_CODIGO_EXITOSO);
        respuestaDTO.setDescripcion(MSG_EXITOSO);
        respuestaDTO.setEsExitoso(true);
        respuestaDTO.setData(tareasSimuladas);

        when(tareaService.obtenerTareas()).thenReturn(respuestaDTO);

        RespuestaDTO<List<TareaDTO>> respuesta = tareaController.obtenerTareas().getBody();

        assertNotNull(respuesta);
        assertEquals(RES_CODIGO_EXITOSO, respuesta.getCodigo());
        assertEquals(MSG_EXITOSO, respuesta.getDescripcion());
        assertTrue(respuesta.isEsExitoso());
        assertNotNull(respuesta.getData());
        assertEquals(1, respuesta.getData().size());
        assertEquals(1, respuesta.getData().get(0).getId());
        assertEquals("Tarea inicial", respuesta.getData().get(0).getTitulo());
        assertEquals("Descripción de la tarea inicial", respuesta.getData().get(0).getDescripcion());

        verify(tareaService, times(1)).obtenerTareas();
    }

    @Test
    void testActualizarTarea() {
        TareaDTO tareaDTO = new TareaDTO();
        tareaDTO.setId(1);
        tareaDTO.setTitulo("Tarea actualizada");
        tareaDTO.setDescripcion("Descripción de la tarea actualizada");

        RespuestaDTO<TareaDTO> respuestaDTO = new RespuestaDTO<>();
        respuestaDTO.setCodigo(RES_CODIGO_EXITOSO);
        respuestaDTO.setDescripcion(MSG_EXITOSO);
        respuestaDTO.setEsExitoso(true);
        respuestaDTO.setData(tareaDTO);

        when(tareaService.actualizarTarea(eq(1), any(TareaDTO.class))).thenReturn(respuestaDTO);

        ResponseEntity<RespuestaDTO> respuesta = tareaController.actualizarTarea(1, tareaDTO);

        assertNotNull(respuesta);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals(RES_CODIGO_EXITOSO, respuesta.getBody().getCodigo());
        assertEquals(MSG_EXITOSO, respuesta.getBody().getDescripcion());
        assertTrue(respuesta.getBody().isEsExitoso());
        assertEquals(tareaDTO, respuesta.getBody().getData());

        verify(tareaService, times(1)).actualizarTarea(eq(1), any(TareaDTO.class));
    }

    @Test
    public void testEliminarTarea() {
        int id = 1;

        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setCodigo("0");
        respuestaDTO.setDescripcion("Exitoso");
        respuestaDTO.setEsExitoso(true);
        respuestaDTO.setData(null);

        when(tareaService.eliminarTarea(id)).thenReturn(respuestaDTO);

        ResponseEntity<RespuestaDTO> respuesta = tareaController.eliminarTarea(id);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals(RES_CODIGO_EXITOSO, respuesta.getBody().getCodigo());
        assertEquals(MSG_EXITOSO, respuesta.getBody().getDescripcion());
        assertTrue(respuesta.getBody().isEsExitoso());
        assertNull(respuesta.getBody().getData());

        verify(tareaService, times(1)).eliminarTarea(id);
    }

}
