package AppAvionets.java.AppAvionets.airports;

public class AirportMapper {
    public static Airport toEntity(AirportRequestDTO airportRequestDTO){
        return new Airport(
                airportRequestDTO.codeAirport(),
                airportRequestDTO.name(),
                airportRequestDTO.city(),
                airportRequestDTO.country());
    }

    public static AirportResponseDTO toResponseDto(Airport airport){
        return new AirportResponseDTO(
                airport.getId(),
                airport.getCodeAirport(),
                airport.getName(),
                airport.getCity(),
                airport.getCountry());
    }
}
