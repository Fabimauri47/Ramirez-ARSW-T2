package edu.eci.escuelaing.arsw.weather.cache;

import org.json.JSONObject;


public interface CacheServicio {

    void salvarRegistroPorNombreCiudad(String name, JSONObject ciudad);

    JSONObject obtenerRegistroporNombreCiudad(String name);

    boolean estaEnCache(String name);

}
