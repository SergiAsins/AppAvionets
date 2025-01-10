package AppAvionets.java.AppAvionets.services;

import AppAvionets.java.AppAvionets.dto.AirportRequestDTO;
import AppAvionets.java.AppAvionets.dto.AirportResponseDTO;
import AppAvionets.java.AppAvionets.entities.Airport;
import AppAvionets.java.AppAvionets.exceptions.AirCompanyNotFoundException;
import AppAvionets.java.AppAvionets.mappers.AirportMapper;
import AppAvionets.java.AppAvionets.repositories.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public AirportResponseDTO createAirport(AirportRequestDTO airportRequestDTO){
        Airport airport = AirportMapper.toEntity(airportRequestDTO);
        Airport savedAirport = airportRepository.save(airport);
        return AirportMapper.toResponseDto(savedAirport);
    }

    public List<AirportResponseDTO> findAll(){
        List<Airport> airportList = airportRepository.findAll();
        return airportList.stream()
                .map(AirportMapper::toResponseDto).toList();
    }

    public AirportResponseDTO findById(Long id){
        Optional<Airport> optionalAirport = airportRepository.findById(id);

        if (optionalAirport.isPresent()){
            throw new AirCompanyNotFoundException("The Airport with the id" + id + "does not exist.");
        }
        Airport airport = optionalAirport.get();
        return AirportMapper.toResponseDto(airport);
    }

    //findByNameIgnoreCaseContaining

    public AirportResponseDTO updateAirportById(Long id, AirportRequestDTO airportRequestDTO){
        Optional<Airport> optionalAirport = airportRepository.findById(id);

        if(optionalAirport.isPresent()){
            Airport airport = optionalAirport.get();

            airport.setName(airportRequestDTO.name());
            airport.setCity(airportRequestDTO.city());
            airport.setCountry(airportRequestDTO.country());

            Airport updatedAirport = airportRepository.save(airport);
            return AirportMapper.toResponseDto((updatedAirport));
        }
        throw new AirCompanyNotFoundException("The airport with the id" + id + "does not exist.");
    }

    public void deleteAirportById(Long id){
        Optional<Airport> optionalAirport = airportRepository.findById(id);

        if(optionalAirport.isPresent()){
            throw new AirCompanyNotFoundException("The airport with id" + "does not exist.");
        }
        airportRepository.deleteById(id);
    }
}
