package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketDto;
import cz.fi.muni.pa036.airticketbooking.entity.FlightTicket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 *
 * @author Lukáš Valach
 */
public class FlightTicketConverter {

        /**
     * Convert FlightTicke DTO to FlightTicket entity.
     *
     * @param flightTicketDto FlightTicketDto
     * @return flightTicket FlightTicket
     */
    public static FlightTicket flightTicketDtoToEntity(FlightTicketDto flightTicketDto) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(flightTicketDto, FlightTicket.class);
    }

    /**
     * Convert FlightTicket entity to FlightTicket DTO.
     *
     * @param flightTicket FlightTicket
     * @return FlightTicketDto flightTicketDto
     */
    public static FlightTicketDto flightTicketEntityToDto(FlightTicket flightTicket) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(flightTicket, FlightTicketDto.class);
    }

    /**
     * Convert list of flightTickets to list of flightTicketDtos
     *
     * @param flightTicketList List<FlightTicket>
     * @return flightTicketDtoList List<FlightTicketDto>
     */
    public static List<FlightTicketDto> flightTicketEntityToDtoList(List<FlightTicket> flightTicketList) {
        List<FlightTicketDto> flightTicketDtoList = new ArrayList<>();
        for (FlightTicket flightTicket : flightTicketList) {
            flightTicketDtoList.add(FlightTicketConverter.flightTicketEntityToDto(flightTicket));
        }
        return flightTicketDtoList;
    }

    /**
     * Convert list of flightTicketDtos to list of flightTickets
     *
     * @param flightTicketDtoList List<FlightTicketDto>
     * @return flightTicketList List<FlightTicket>
     */
    public static List<FlightTicket> flightTicketDtoToEntityList(List<FlightTicketDto> flightTicketDtoList) {
        List<FlightTicket> flightTicketList = new ArrayList<>();
        for (FlightTicketDto flightTicketDto : flightTicketDtoList) {
            flightTicketList.add(FlightTicketConverter.flightTicketDtoToEntity(flightTicketDto));
        }
        return flightTicketList;
    }

    /**
     * Convert map of flightTickets to map of flightTicketDtos
     *
     * @param Map<FlightTicket, Integer> flightTicketEntityMap
     * @return Map<FlightTicketDto, Integer> flightTicketDtoMap
     */
    public static Map<FlightTicketDto, Integer> flightTicketEntityToDtoMap(Map<FlightTicket, Integer> flightTicketEntityMap) {
        Map<FlightTicketDto, Integer> flightTicketDtoMap = new HashMap<FlightTicketDto, Integer>();
        for (FlightTicket flightTicket : flightTicketEntityMap.keySet()) {
            flightTicketDtoMap.put(FlightTicketConverter.flightTicketEntityToDto(flightTicket), flightTicketEntityMap.get(flightTicket));
        }
        return flightTicketDtoMap;
    }

    /**
     * Convert map of flightTickets to list of FlightTicketDtos
     *
     * @param flightTicketEntityMap Map<FlightTicket, Integer>
     * @return flightTicketEntityList List<FlightTicketDto>
     */
    public static List<FlightTicketDto> flightTicketEntityMapToDto(Map<FlightTicket, Integer> flightTicketEntityMap) {
        List<FlightTicketDto> flightTicketDtoList = new ArrayList<>();
        for (FlightTicket flightTicket : flightTicketEntityMap.keySet()) {
            FlightTicketDto flightTicketDto = FlightTicketConverter.flightTicketEntityToDto(flightTicket);
            flightTicketDtoList.add(flightTicketDto);
        }
        return flightTicketDtoList;
    }

    /**
     * Convert map of flightTicketDtos to map of flightTickets
     *
     * @param flightTicketDaoMap Map<FlightTicketDto, Integer>
     * @return flightTicketEntityMap Map<FlightTicket, Integer>
     */
    public static Map<FlightTicket, Integer> flightTicketDtoToEntityMap(Map<FlightTicketDto, Integer> flightTicketDaoMap) {
        Map<FlightTicket, Integer> flightTicketEntityMap = new HashMap<FlightTicket, Integer>();
        for (FlightTicketDto flightTicketDto : flightTicketDaoMap.keySet()) {
            flightTicketEntityMap.put(FlightTicketConverter.flightTicketDtoToEntity(flightTicketDto), flightTicketEntityMap.get(flightTicketDto));
        }
        return flightTicketEntityMap;
    }
}
