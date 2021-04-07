package edu.eci.escuelaing.arsw.weather.servicios;

import org.json.JSONObject;

public interface ClimaServicio {

    JSONObject obtenerEstadisticas(String ciudad) throws climaServicioËxcepcion;

}
