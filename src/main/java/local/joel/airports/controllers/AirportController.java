/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.joel.airports.controllers;

import java.util.List;
import local.joel.airports.service.AirportService;
import local.joel.airports.entities.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sesideva
 */
@RestController
public class AirportController {

    @Autowired
    private AirportService airportService;

    /**
     * Endpoint /airports/airport
     * Retorna TODOS os aeroportos da base de dados.
     * @return
     */
    @GetMapping("/airport")
    public List<Airport> findAll() {
        List<Airport> result = airportService.findAll();
        return result;
    }
    @GetMapping("/city/{cityName}")
    public ResponseEntity<List<Airport>> findByCityIgnoreCase(@PathVariable String cityName) {
    List<Airport> result = airportService.findByCity(cityName);

    if (result.isEmpty()) {
        // Ops.. lista vazia...
        // notFound devolve 404
        return ResponseEntity.notFound().build();
    } else {
        // Eba! Tem dados!
        // ok devolve 200
        return ResponseEntity.ok(result);
    }
}

}
