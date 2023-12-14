package ch.supsi.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"}) @ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String surname;
    @Column(unique=true)
    private String username;
    private String password;
    private String salt;
    private double salary;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
