package com.picpaysimplify.picpaysimplify.domain.user;

import com.picpaysimplify.picpaysimplify.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)

    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO dto) {
        this.firstName = dto.firstName();
        this.lastName = dto.lastName();
        this.document = dto.document();
        this.email = dto.email();
        this.password =dto.password();
        this.balance = dto.balance();
        this.userType = dto.userType();
    }
}
