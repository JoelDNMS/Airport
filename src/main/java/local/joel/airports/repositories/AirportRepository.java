/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package local.joel.airports.repositories;

import java.util.List;
import local.joel.airports.entities.Airport;
import local.joel.airports.projections.AirportNearMeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 *
 * @author sesideva
 */
public interface AirportRepository extends JpaRepository<Airport, Long> {
    
    List<Airport> findByCityIgnoreCase(String city);
    List<Airport> findByCountryIgnoreCase(String country);
    
    Airport findByIataCode(String iataCode);
    
    @Query(nativeQuery = true, value = """
    SELECT 
        airport.id,
        airport.name,
        airport.city,
        airport.iatacode,
        airport.latitude,
        airport.longitude,
        airport.altitude,
        SQRT(
            power(airport.latitude - :latOrigem, 2) + 
            power(airport.longitude - :lonOrigem, 2)) * 60 * 1.852 as distanciaKM
    from AIRPORT
    order by distanciaKM
    limit 10;
""")
List<AirportNearMeProjection> findNearMe(double latOrigem, double lonOrigem);

}
    


