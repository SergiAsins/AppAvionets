package AppAvionets.java.AppAvionets.mappers;

import AppAvionets.java.AppAvionets.dto.AirportRequestDTO;
import AppAvionets.java.AppAvionets.dto.AirportResponseDTO;
import AppAvionets.java.AppAvionets.entities.Airport;

public class AirportMapper {
    public static Airport toEntity(AirportRequestDTO airportRequestDTO){
        return new Airport(
                airportRequestDTO.name(),
                airportRequestDTO.city(),
                airportRequestDTO.country());
    }

    public static AirportResponseDTO toResponseDto(Airport airport){
        return new AirportResponseDTO(
                airport.getId(),
                airport.getName(),
                airport.getCity(),
                airport.getCountry());
    }
}
