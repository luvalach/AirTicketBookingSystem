/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.api.dto;

import java.util.Objects;
import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Lukáš Valach
 */
public class UserDto {

    /**
     * The ID of the User
     */
    private Long id;

    /**
     * The user's nick.
     */
    @NotBlank
    @Length(max = 20)
    private String nick;

    /**
     * The user's first name.
     */
    @NotNull
    @Length(max = 20)
    private String firstName;

    /**
     * The user's surname.
     */
    @NotNull
    @Length(max = 20)
    private String surname;

    /**
     * The description of the user.
     */
    @Length(max = 200)
    private String description;

    /**
     * The user's password.
     */
//    @NotBlank
//    @Length(min = 4, max = 20)
    private String password;
    
    private String role;
    
    /**
     * @return the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the id.
     */
    public String getNick() {
        return nick;
    }

    /**
     * @param nick the nick to be set.
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * @return the id.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to be set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to be set.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.nick);
        hash = 23 * hash + Objects.hashCode(this.firstName);
        hash = 23 * hash + Objects.hashCode(this.surname);
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + Objects.hashCode(this.password);
        hash = 23 * hash + Objects.hashCode(this.role);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDto other = (UserDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nick, other.nick)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return true;
    }
 

}
