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
    //private String salt;
    private double salary;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /*@PrePersist
    void generateSalt(){
        Random r = new Random();
        String alphabet = "1234567890abcdefghilmnopqrstuvzABCDEFGHILMNOPQRTSTUVZjJkKxX-_!?$£";
        StringBuilder sb = new StringBuilder();

        // Generale 8 char long salt
        for (int i = 0; i < 8; i++) {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }

        this.salt = sb.toString();
    }*/
}
