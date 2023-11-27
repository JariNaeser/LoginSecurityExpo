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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Column(unique=true)
    private String username;
    private String password;
    @OneToOne
    private Salary salary;

    public User(String username, String password, String name, String surname, Salary salary){
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

}
