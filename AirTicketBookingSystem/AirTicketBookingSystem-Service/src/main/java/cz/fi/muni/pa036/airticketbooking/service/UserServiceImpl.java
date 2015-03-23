package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.service.UserService;
import cz.fi.muni.pa036.airticketbooking.converter.UserConverter;

import cz.fi.muni.pa036.airticketbooking.dao.UserDao;
import cz.fi.muni.pa036.airticketbooking.api.dto.UserDto;
import cz.fi.muni.pa036.airticketbooking.dao.UserRoleDao;
import cz.fi.muni.pa036.airticketbooking.entity.User;
import cz.fi.muni.pa036.airticketbooking.entity.UserRole;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukáš Valach
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UserConverter userConverter;

    @Transactional
    @Override
    public long save(UserDto userDto) {
        if (userDto == null) {
            throw new NullPointerException();
        }
        UserRole userRole = new UserRole();
        User user = userConverter.userDtoToEntity(userDto);
        userRole.setUser(user);
        userRole.setRole(userDto.getRole());
        Long id = userDao.save(user);
        userRoleDao.save(userRole);
        return id;

    }

    @Transactional
    @Override
    public UserDto update(UserDto userDto) {
        if (userDto == null) {
            throw new NullPointerException();
        }
        User user = userConverter.userDtoToEntity(userDto);
        UserRole userRole = userRoleDao.findByUser(user);
        userRole.setRole(userDto.getRole());
        userRoleDao.update(userRole);
        return userConverter.userEntityToDto(userDao.update(user));
    }

    @Transactional
    @Override
    public void delete(UserDto userDto) {
        if (userDto == null) {
            throw new NullPointerException();
        }
        User user = userConverter.userDtoToEntity(userDto);
        UserRole userRole = userRoleDao.findByUser(user);
        userRoleDao.delete(userRole);
        userDao.delete(user);
    }

    @Transactional
    @Override
    public UserDto find(long id) {
        User user = userDao.find(id);
        UserRole userRole = userRoleDao.findByUser(user);
        UserDto userDto = userConverter.userEntityToDto(user);
        userDto.setRole(userRole.getRole());
        return userDto;
    }

    @Transactional
    @Override
    public List<UserDto> findAll() {
        List<User> userList = userDao.findAll();
        List<UserDto> resultList = new ArrayList();
        for (User user : userList) {
            UserDto userDto = userConverter.userEntityToDto(user);
            UserRole userRole = userRoleDao.findByUser(user);
            userDto.setRole(userRole.getRole());
            resultList.add(userDto);
        }
        return resultList;
    }

    @Transactional
    @Override
    public UserDto findByNick(String nick) {
        User user = userDao.findByNick(nick);
        UserRole userRole = userRoleDao.findByUser(user);
        UserDto userDto = userConverter.userEntityToDto(user);
        userDto.setRole(userRole.getRole());
        return userDto;
    }
}
