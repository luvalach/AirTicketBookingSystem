/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.UserDto;
import cz.fi.muni.pa036.airticketbooking.api.service.UserService;
import cz.fi.muni.pa036.airticketbooking.api.service.SecurityService;
import java.util.Collection;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lukáš Valach
 */
@Service
public class SecurityServiceImpl implements SecurityService{

    @Autowired
    UserService userService;

    public UserDto getCurrentlyLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return userService.findByNick(name); //get user by login name
    }
    
    public boolean isCurrentlyLoggedUserAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
        Iterator<SimpleGrantedAuthority> authoritiesIterator = authorities.iterator();
        while (authoritiesIterator.hasNext()){
            if (authoritiesIterator.next().getAuthority().equals("ROLE_ADMIN")){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasPermissionToModifyEntity(Long entityId) {
        Long currentUserId = this.getCurrentlyLoggedUser().getId();
        boolean isAdmin = this.isCurrentlyLoggedUserAdmin();
        if (!isAdmin && currentUserId != entityId){
            return false;
        }
        return true;
    }
            
}
