package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightPriceDto;
import cz.fi.muni.pa036.airticketbooking.entity.FlightPrice;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Converts FlightPrice entity to DTO and vice versa.
 * 
 * @author Tomas Smetanka
 */
public class FlightPriceConverter {

     public static FlightPrice flightPriceDtoToEntity(FlightPriceDto flightPriceDto) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(flightPriceDto, FlightPrice.class);
    }
     
    public static FlightPriceDto flightPriceEntityToDto(FlightPrice flightPrice) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(flightPrice, FlightPriceDto.class);
    }
     
}
