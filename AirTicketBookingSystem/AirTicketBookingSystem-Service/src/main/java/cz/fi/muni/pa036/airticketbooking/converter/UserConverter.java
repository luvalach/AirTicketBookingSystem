package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.UserDto;
import cz.fi.muni.pa036.airticketbooking.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukáš Valach
 */
@Component
public class UserConverter {

    /**
     * Convert User DTO to User entity.
     *
     * @param userDto DTO
     * @return User entity
     */
    public User userDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setDescription(userDto.getDescription());
        user.setFirstName(userDto.getFirstName());
        user.setId(userDto.getId());
        user.setNick(userDto.getNick());
        user.setPassword(userDto.getPassword());
        user.setSurname(userDto.getSurname());
        return user;
    }

    /**
     * Convert User entity to User DTO.
     *
     * @param user entity
     * @return User Dto
     */
    public UserDto userEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setDescription(user.getDescription());
        userDto.setFirstName(user.getFirstName());
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setSurname(user.getSurname());
        userDto.setNick(user.getNick());
        return userDto;
    }

    /**
     * Convert User entity List to User DTO List.
     *
     * @param userList entities list
     * @return User Dto list
     */
    public List<UserDto> userEntityToDtoList(List<User> userList) {
        List<UserDto> userDaoList = new ArrayList<>();
        for (User user : userList) {
            userDaoList.add(this.userEntityToDto(user));
        }
        return userDaoList;
    }
}
