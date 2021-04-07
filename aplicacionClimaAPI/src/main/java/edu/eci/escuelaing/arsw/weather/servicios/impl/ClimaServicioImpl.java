package edu.eci.escuelaing.arsw.weather.servicios.impl;

import edu.eci.escuelaing.arsw.weather.servicios.ClimaServicio;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("climaServicio")
public class ClimaServicioImpl implements ClimaServicio {

    @Autowired
    CacheServicio cacheServicio;

    private Ciudad consultarApiClimaPorCiudad(String ciudad) throws climaServicioËxcepcion {
        JSONObject jsonObject = obternerEstadisticas(ciudad);
        Gson gson = new Gson();
        Ciudad ciudadObjetivo= new Ciudad();
        ciudadObjetivo.setViento(gson.fromJson(String.valueOf(jsonObject.getJSONObject("wind")), Viento.class));
        ciudadObjetivo.setVisibilidad(gson.fromJson(String.valueOf(jsonObject.get("visibility")), Visibilidad.class));
        ciudadObjetivo.setNombre(gson.fromJson(String.valueOf(jsonObject.get("name")), String.class));
        ciudadObjetivo.setCoordenadas(gson.fromJson(String.valueOf(jsonObject.getJSONObject("coord")), Locacion.class));
        ciudadObjetivo.setClima(gson.fromJson(String.valueOf(jsonObject.getJSONArray("weather").getJSONObject(0)), Clima.class));
        return ciudadObjetivo;
    }


    public JSONObject obternerEstadisticas(String ciudad) throws climaServicioËxcepcion {
        HttpResponse<String> respuesta = null;
        try {
            respuesta = Unirest.get("https://api.openweathermap.org/data/2.5/weather?q=" + ciudad + "&appid=6196b9e3c9c293bfacfc6e506169df11").asString();
            return new JSONObject(respuesta.getBody());
        } catch (UnirestException e) {
            throw new climaServicioËxcepcion("La ciudad no existe");
        }

    }


    @Override
    public Ciudad obtenerEstadisticas(String ciudad) throws climaServicioËxcepcion {
        if (cacheServicio.obtenerCachePorCiudad(ciudad) == null || System.currentTimeMillis() - cacheServicio.obtenerTiempoCache(ciudad) >= 300000) {
            Ciudad ciudadObjetivo = consultarApiClimaPorCiudad(ciudad);
            cacheServicio.salvarCacheCiudad(ciudad, ciudadObjetivo);
        }
        return cacheServicio.obtenerCachePorCiudad(ciudad);

    }






}
