package edu.eci.escuelaing.arsw.weather.controlador;

import edu.eci.escuelaing.arsw.weather.servicios.ClimaServicio;
import edu.eci.escuelaing.arsw.weather.servicios.climaServicioËxcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api")
public class ControladorClimaAPI {

    @Autowired
    @Qualifier("climaServicio")
    ClimaServicio climaServicio;

    @GetMapping("/ciudades/{nombre}")
    public ResponseEntity<?> getStatistics(@PathVariable String nombre) {
        try {
            return new ResponseEntity<>(climaServicio.obtenerEstadisticas(nombre), HttpStatus.ACCEPTED);
        } catch (climaServicioËxcepcion e) {
            Logger.getLogger(ControladorClimaAPI.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
