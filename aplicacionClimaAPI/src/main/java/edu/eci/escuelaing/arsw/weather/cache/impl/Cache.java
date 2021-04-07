package edu.eci.escuelaing.arsw.weather.cache.impl;

import edu.eci.escuelaing.arsw.weather.cache.CacheServicio;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class Cache implements CacheServicio {

    ConcurrentHashMap<String,JSONObject> cache;

    public Cache(){

        cache = new ConcurrentHashMap<>();
    }

    @Override
    public void salvarRegistroPorNombreCiudad(String name, JSONObject ciudad) {
        cache.put(name,ciudad);
    }

    @Override
    public JSONObject obtenerRegistroporNombreCiudad(String name) {
        return cache.get(name);
    }

    @Override
    public boolean estaEnCache(String name) {
        return cache.containsKey(name);
    }

}
