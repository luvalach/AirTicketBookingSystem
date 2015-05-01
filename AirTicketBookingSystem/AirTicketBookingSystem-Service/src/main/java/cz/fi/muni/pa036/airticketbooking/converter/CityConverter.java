package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.CityDto;
import cz.fi.muni.pa036.airticketbooking.entity.City;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 *
 * @author Lukáš Valach
 */
public class CityConverter {

        /**
     * Convert FlightTicke DTO to City entity.
     *
     * @param cityDto CityDto
     * @return city City
     */
    public static City cityDtoToEntity(CityDto cityDto) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(cityDto, City.class);
    }

    /**
     * Convert City entity to City DTO.
     *
     * @param city City
     * @return CityDto cityDto
     */
    public static CityDto cityEntityToDto(City city) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(city, CityDto.class);
    }

    /**
     * Convert list of citys to list of cityDtos
     *
     * @param cityList List<City>
     * @return cityDtoList List<CityDto>
     */
    public static List<CityDto> cityEntityToDtoList(List<City> cityList) {
        List<CityDto> cityDtoList = new ArrayList<>();
        for (City city : cityList) {
            cityDtoList.add(CityConverter.cityEntityToDto(city));
        }
        return cityDtoList;
    }

    /**
     * Convert list of cityDtos to list of citys
     *
     * @param cityDtoList List<CityDto>
     * @return cityList List<City>
     */
    public static List<City> cityDtoToEntityList(List<CityDto> cityDtoList) {
        List<City> cityList = new ArrayList<>();
        for (CityDto cityDto : cityDtoList) {
            cityList.add(CityConverter.cityDtoToEntity(cityDto));
        }
        return cityList;
    }
}
