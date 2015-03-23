/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.rest.HashCode;
import cz.fi.muni.pa036.airticketbooking.api.dto.UserDto;
import cz.fi.muni.pa036.airticketbooking.api.service.UserService;
import cz.fi.muni.pa036.airticketbooking.api.service.SecurityService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import org.springframework.security.access.AccessDeniedException;
/**
 *
 * @author Lukáš Valach
 */
@RestController
@RequestMapping("/user")
public class UserRest {
    @Autowired
    UserService userService;
    
    @Autowired
    SecurityService securityService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getUserList() {
	List<UserDto> userList = userService.findAll();
        if (userList == null) {
            userList = new ArrayList<>();
        }
        return userList;
    }
    
    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public UserDto getUserDetail(@PathVariable Long userId) {
        return userService.find(userId);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public List<Long> createUser(@RequestBody @Valid UserDto user) {
  	List<Long> resultList = new ArrayList<>();
        String hashedPassword = HashCode.getHashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        resultList.add(userService.save(user));
        return resultList;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UserDto updateUser(@RequestBody @Valid UserDto user) {
        Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        if (!securityService.hasPermissionToModifyEntity(user.getId())) {
            throw new AccessDeniedException("Access denied: User " + currentUserId + " cannot update user " + user.getId());
        }
        String hashedPassword = HashCode.getHashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userService.update(user);
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long userId) {
	UserDto user = userService.find(userId);
        Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        if (!securityService.hasPermissionToModifyEntity(user.getId())) {
            throw new AccessDeniedException("Access denied: User " + currentUserId + " cannot delete user " + user.getId());
        }
        userService.delete(user);
    }
}
