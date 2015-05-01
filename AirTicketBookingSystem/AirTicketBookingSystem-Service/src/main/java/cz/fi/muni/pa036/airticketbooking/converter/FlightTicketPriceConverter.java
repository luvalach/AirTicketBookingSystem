package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightPriceDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketPriceDto;
import cz.fi.muni.pa036.airticketbooking.entity.FlightPrice;
import cz.fi.muni.pa036.airticketbooking.entity.FlightTicketPrice;
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
public class FlightTicketPriceConverter {

        /**
     * Convert FlightTicketPrice DTO to FlightTicketPrice entity.
     *
     * @param flightTicketPriceDto FlightTicketPriceDto
     * @return flightTicketPrice FlightTicketPrice
     */
    public static FlightTicketPrice flightTicketPriceDtoToEntity(FlightTicketPriceDto flightTicketPriceDto) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(flightTicketPriceDto, FlightTicketPrice.class);
    }

    /**
     * Convert FlightTicketPrice entity to FlightTicketPrice DTO.
     *
     * @param flightTicketPrice FlightTicketPrice
     * @return FlightTicketPriceDto flightTicketPriceDto
     */
    public static FlightTicketPriceDto flightTicketPriceEntityToDto(FlightTicketPrice flightTicketPrice) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(flightTicketPrice, FlightTicketPriceDto.class);
    }

    /**
     * Convert list of flightTicketPrices to list of flightTicketPriceDtos
     *
     * @param flightTicketPriceList List<FlightTicketPrice>
     * @return flightTicketPriceDtoList List<FlightTicketPriceDto>
     */
    public static List<FlightTicketPriceDto> flightTicketPriceEntityToDtoList(List<FlightTicketPrice> flightTicketPriceList) {
        List<FlightTicketPriceDto> flightTicketPriceDtoList = new ArrayList<>();
        for (FlightTicketPrice flightTicketPrice : flightTicketPriceList) {
            flightTicketPriceDtoList.add(FlightTicketPriceConverter.flightTicketPriceEntityToDto(flightTicketPrice));
        }
        return flightTicketPriceDtoList;
    }

    /**
     * Convert list of flightTicketPriceDtos to list of flightTicketPrices
     *
     * @param flightTicketPriceDtoList List<FlightTicketPriceDto>
     * @return flightTicketPriceList List<FlightTicketPrice>
     */
    public static List<FlightTicketPrice> flightTicketPriceDtoToEntityList(List<FlightTicketPriceDto> flightTicketPriceDtoList) {
        List<FlightTicketPrice> flightTicketPriceList = new ArrayList<>();
        for (FlightTicketPriceDto flightTicketPriceDto : flightTicketPriceDtoList) {
            flightTicketPriceList.add(FlightTicketPriceConverter.flightTicketPriceDtoToEntity(flightTicketPriceDto));
        }
        return flightTicketPriceList;
    }

    /**
     * Convert map of flightTicketPrices to map of flightTicketPriceDtos
     *
     * @param Map<FlightTicketPrice, Integer> flightTicketPriceEntityMap
     * @return Map<FlightTicketPriceDto, Integer> flightTicketPriceDtoMap
     */
    public static Map<FlightTicketPriceDto, Integer> flightTicketPriceEntityToDtoMap(Map<FlightTicketPrice, Integer> flightTicketPriceEntityMap) {
        Map<FlightTicketPriceDto, Integer> flightTicketPriceDtoMap = new HashMap<FlightTicketPriceDto, Integer>();
        for (FlightTicketPrice flightTicketPrice : flightTicketPriceEntityMap.keySet()) {
            flightTicketPriceDtoMap.put(FlightTicketPriceConverter.flightTicketPriceEntityToDto(flightTicketPrice), flightTicketPriceEntityMap.get(flightTicketPrice));
        }
        return flightTicketPriceDtoMap;
    }

    /**
     * Convert map of flightTicketPrices to list of FlightTicketPriceDtos
     *
     * @param flightTicketPriceEntityMap Map<FlightTicketPrice, Integer>
     * @return flightTicketPriceEntityList List<FlightTicketPriceDto>
     */
    public static List<FlightTicketPriceDto> flightTicketPriceEntityMapToDto(Map<FlightTicketPrice, Integer> flightTicketPriceEntityMap) {
        List<FlightTicketPriceDto> flightTicketPriceDtoList = new ArrayList<>();
        for (FlightTicketPrice flightTicketPrice : flightTicketPriceEntityMap.keySet()) {
            FlightTicketPriceDto flightTicketPriceDto = FlightTicketPriceConverter.flightTicketPriceEntityToDto(flightTicketPrice);
            flightTicketPriceDtoList.add(flightTicketPriceDto);
        }
        return flightTicketPriceDtoList;
    }

    /**
     * Convert map of flightTicketPriceDtos to map of flightTicketPrices
     *
     * @param flightTicketPriceDaoMap Map<FlightTicketPriceDto, Integer>
     * @return flightTicketPriceEntityMap Map<FlightTicketPrice, Integer>
     */
    public static Map<FlightTicketPrice, Integer> flightTicketPriceDtoToEntityMap(Map<FlightTicketPriceDto, Integer> flightTicketPriceDaoMap) {
        Map<FlightTicketPrice, Integer> flightTicketPriceEntityMap = new HashMap<FlightTicketPrice, Integer>();
        for (FlightTicketPriceDto flightTicketPriceDto : flightTicketPriceDaoMap.keySet()) {
            flightTicketPriceEntityMap.put(FlightTicketPriceConverter.flightTicketPriceDtoToEntity(flightTicketPriceDto), flightTicketPriceEntityMap.get(flightTicketPriceDto));
        }
        return flightTicketPriceEntityMap;
    }

    static FlightPriceDto flightPriceEntityToDto(FlightPrice value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
