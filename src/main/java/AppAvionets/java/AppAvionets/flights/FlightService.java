package AppAvionets.java.AppAvionets.flights;

import AppAvionets.java.AppAvionets.airports.Airport;
import AppAvionets.java.AppAvionets.exceptions.AirCompanyAlreadyExistsException;
import AppAvionets.java.AppAvionets.exceptions.AirCompanyInvalidFormatException;
import AppAvionets.java.AppAvionets.exceptions.AirCompanyNotFoundException;
import AppAvionets.java.AppAvionets.airports.AirportRepository;
import AppAvionets.java.AppAvionets.exceptions.flights.AirCompanyErrorFlightException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final AirportRepository airportRepository;

    private final FlightRepository flightRepository;

    public FlightService(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    public FlightResponseDTO createFlight(FlightRequestDTO flightRequestDTO){
        if(flightRequestDTO.airportOriginId() == flightRequestDTO.airportDestinationId()){
            throw new AirCompanyErrorFlightException("Bro? A flight can not start and finish in the same place");
        }
        if (!flightRequestDTO.flightNumber().matches("^[A-Z]{2}\\d{3}$")) {
            throw new AirCompanyInvalidFormatException("The flightNumber must be two uppercase letters followed by three digits");
        }

        Optional<Flight> existFlight = flightRepository.findByFlightNumberAndOrigin_id(flightRequestDTO.flightNumber(), flightRequestDTO.airportOriginId());
        if(existFlight.isPresent()){
            throw new AirCompanyAlreadyExistsException("There is already a flight with this username linked to this airport.");
        }

        Optional<Airport> originAirport = airportRepository.findById(flightRequestDTO.airportOriginId());
        if (originAirport.isEmpty()) {
            throw new AirCompanyNotFoundException("Origin airport not found.");
        }

        Optional<Airport> destinationAirport = airportRepository.findById(flightRequestDTO.airportDestinationId());
        if (destinationAirport.isEmpty()) {
            throw new AirCompanyNotFoundException("Destination airport not found");
        }

        //Map and save the Flight
        Flight flightToSave = FlightMapper.toEntity(flightRequestDTO, originAirport.get() , destinationAirport.get());
        Flight savedFlight = flightRepository.save(flightToSave);
        return FlightMapper.toResponseDTO(savedFlight);
    }

    public List<FlightResponseDTO> listAllFlights(){
        List<Flight> flightList = flightRepository.findAll();
        List<FlightResponseDTO> responseList = new java.util.ArrayList<>(Collections.emptyList());
        flightList.forEach(flight -> {
            FlightResponseDTO flightResponseDTO = FlightMapper.toResponseDTO(flight);
            responseList.add(flightResponseDTO);
        });

        if(flightList.isEmpty()){
            throw new AirCompanyNotFoundException("There are no flights to show.");
        }
        return responseList;
    }

    public FlightResponseDTO showFlightById(Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);

        if(optionalFlight.isEmpty()) {
            throw new AirCompanyNotFoundException("Flight with id " + id + " does not exist.");
        }
        Flight flight = optionalFlight.get();
        return FlightMapper.toResponseDTO(flight);
    }

    public FlightResponseDTO deleteFlightById(Long id){
        Optional<Flight> optionalFlight = flightRepository.findById(id);

        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();
            flightRepository.deleteById(id);
            return FlightMapper.toResponseDTO(flight);
        } else {
            throw new AirCompanyNotFoundException("The flight with id " + id + " does not exist.");
        }
    }

    public FlightResponseDTO modifyFlight(Long id, FlightRequestDTO flightRequestDTO){
        Optional<Flight> optionalFlight = flightRepository.findById(id);

        if(optionalFlight.isPresent()){
            Flight flight = optionalFlight.get();

            Optional<Airport> originAirportOptional = airportRepository.findById(flightRequestDTO.airportOriginId());
            Optional<Airport> destinationAirportOptional = airportRepository.findById(flightRequestDTO.airportDestinationId());

            if (originAirportOptional.isEmpty()) {
                throw new AirCompanyNotFoundException("Origin airport with ID " + flightRequestDTO.airportOriginId() + " does not exist.");
            }

            if (destinationAirportOptional.isEmpty()) {
                throw new AirCompanyNotFoundException("Destination airport with ID " + flightRequestDTO.airportDestinationId() + " does not exist.");
            }

            flight.setFlightNumber(flightRequestDTO.flightNumber());
            flight.setStatus(flightRequestDTO.status());
            flight.setOrigin(originAirportOptional.get());
            flight.setDestination(destinationAirportOptional.get());
            flight.setDepartureTime(flightRequestDTO.departureTime());
            flight.setArrivalTime(flightRequestDTO.arrivalTime());
            flight.setAvailableSeats(flightRequestDTO.availableSeats());

            Flight updatedFlight = flightRepository.save(flight);
            return FlightMapper.toResponseDTO(updatedFlight);
        }
        throw new AirCompanyNotFoundException("The flight with the id" + id+ "does not exist");
    }

    public List<FlightResponseDTO> searchFlights(String originCodeAirport, String destinationCodeAirport, Timestamp date, Integer seats){
        //Calculates the start and ending of the date:
        Timestamp startOfTheDay = Timestamp.valueOf(date.toLocalDateTime().toLocalDate().atStartOfDay());
        Timestamp endOfTheDay = Timestamp.valueOf(date.toLocalDateTime().toLocalDate().atTime(23, 59, 59));

        //Search flights with these parameters
        List<Flight> flights = flightRepository.findFlightsByCriteria(originCodeAirport, destinationCodeAirport, startOfTheDay, endOfTheDay, seats);

        if(flights.isEmpty()) {
            throw new AirCompanyNotFoundException("No flights available for your actual search. Please try again.");
        }
            return flights.stream()
                    .map(FlightMapper::toResponseDTO)
                    .toList();
    }
}
