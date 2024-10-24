package com.bdb.gestortareasws.utilitarios;

public class Constantes {

    private Constantes() {}

    /*
     * Codigos de respuestas
     */
    public static final String RES_CODIGO_EXITOSO = "0";
    public static final String RES_CODIGO_ERROR_GENERICO = "1";
    public static final String RES_CODIGO_ERROR_SIN_DATOS = "2";
    public static final String RES_CODIGO_ERROR_DATOS_INCOMPLETOS = "3";
    public static final String RES_CODIGO_ERROR_NO_AUTORIZADO = "4";

    /**
     * Swagger
     */
    public static final String DESC_RESPUESTA_CODIGO = "Codigo de respuesta del consumo: \n"
            +"\n"
            +"+ `0` - transacción exitosa. Status OK:200\n"
            +"+ `1` - transacción fallida, error no controlado. Status INTERNAL SERVER ERROR:500\n"
            +"+ `2` - transacción fallida, no se encontro información. Status NOT FOUND:404\n"
            +"+ `3` - transacción fallida, datos de entrada imcompletos o erroneos. BAD REQUEST:400\n"
            +"+ `4` - transacción fallida, no Autorizado: Token inválido. BAD REQUEST:401\n"
            +"\n";

    public static final String RESP_404 = "{\n" + //
            "    \"codigo\": \"2\",\n" + //
            "    \"descripcion\": \"No exitoso: Error, no se encontro información\",\n" + //
            "    \"esExitosa\": false,\n" + //
            "    \"servidor\": \"bodmonto4\"\n" + //
            "}";

    /*
     * Mensajes de respuesta
     */
    public static final String MSG_EXITOSO = "Exitoso";
    public static final String MSG_ERROR_NO_AUTORIZADO= "No Autorizado: Token inválido";
    public static final String MSG_ERROR_NO_MANEJADO = "Error no manejado";
    public static final String MSG_ERROR_SIN_DATOS = "Error, no se encontro información";
    public static final String MSG_ERROR_SIN_DATOS_ENTRADA = "Error, la información requerida esta imcompleta";

}
