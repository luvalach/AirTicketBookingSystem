package cz.fi.muni.pa036.airticketbooking.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The User Entity.
 *
 * @author Lukáš Valach
 */
@Entity
@Table(name = "user_acount")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The ID of the User
     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
    @SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq", allocationSize = 1)
    private Long id;

    /**
     * The user's nick.
     */
    @Column(nullable = false, unique = true)
    private String nick;

    /**
     * The user's first name.
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * The user's surname.
     */
    @Column(nullable = false)
    private String surname;

    /**
     * The description of the user.
     */
    @Column(nullable = true)
    private String description;

    /**
     * The user's account is enabled(accessible) if enabled=1, otherwise is
     * disabled.
     */
    @Column(nullable = false, columnDefinition = "int default 1")
    private int enabled = 1;

    /**
     * The user's password.
     */
//    @NotBlank
//    @Length(min = 4, max = 20)
    @Column(nullable = true)
    private String password;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.nick);
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.surname);
        hash = 29 * hash + Objects.hashCode(this.description);
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
        final User other = (User) obj;
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
        return true;
    }

}
