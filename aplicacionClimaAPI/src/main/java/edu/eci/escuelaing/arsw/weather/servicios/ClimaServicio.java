package edu.eci.escuelaing.arsw.weather.servicios;

import org.json.JSONObject;

public interface ClimaServicio {

    Ciudad obtenerEstadisticas(String ciudad) throws climaServicioËxcepcion;

}
