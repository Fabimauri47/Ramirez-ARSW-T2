package edu.eci.escuelaing.arsw.weather.servicios.impl;

import edu.eci.escuelaing.arsw.weather.cache.CacheServicio;
import edu.eci.escuelaing.arsw.weather.servicios.ClimaServicio;
import edu.eci.escuelaing.arsw.weather.servicios.climaServicioËxcepcion;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

@Service("climaServicio")
public class ClimaServicioImpl implements ClimaServicio {

    @Autowired
    CacheServicio cacheServicio;

    public JSONObject obternerEstadisticas(String ciudad) throws climaServicioËxcepcion {
        HttpResponse<String> respuesta;
        try {
            respuesta = Unirest.get("https://api.openweathermap.org/data/2.5/weather?q=" + ciudad + "&appid=6196b9e3c9c293bfacfc6e506169df11").asString();
            return new JSONObject(respuesta.getBody());
        } catch (UnirestException | JSONException e) {
            throw new climaServicioËxcepcion("La ciudad no existe");
        }

    }

    @Override
    public String obtenerEstadisticas(String ciudad) throws climaServicioËxcepcion {
        if (!cacheServicio.estaEnCache(ciudad)){
            JSONObject ciudadObjetivo = obternerEstadisticas(ciudad);
            cacheServicio.salvarRegistroPorNombreCiudad(ciudad, ciudadObjetivo);
        }
        return cacheServicio.obtenerRegistroporNombreCiudad(ciudad).toString();
    }






}
