package AppAvionets.java.AppAvionets.airports;

public record AirportResponseDTO (
        Long id,
        String codeAirport,
        String name,
        String city,
        String country
){
}
