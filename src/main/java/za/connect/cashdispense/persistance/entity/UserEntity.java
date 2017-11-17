package za.connect.cashdispense.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = UserEntity.TABLE_NAME,
        indexes = {@Index(name = UserEntity.TABLE_NAME + "_id_idx", columnList = "id", unique = true)})
public class UserEntity {

    public static final String TABLE_NAME = "user";
    private static final String SEQUENCE_NAME = TABLE_NAME + "_seq";

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    private Long id;
    private String name;
    private String password;
    private String username;

    public UserEntity() {
    }

    public UserEntity(final String name, final String password, final String userName) {
        this.name = name;
        this.password = password;
        this.username = userName;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
