package ua.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.example.dtos.UserDTO;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String name;
    private String surname;
    private int age;
    private String phoneNumber;
    private String password;
    private boolean enabled;

    public UserDTO getDTO() {
        return UserDTO.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .age(age)
                .phoneNumber(phoneNumber)
                .build();
    }
}
