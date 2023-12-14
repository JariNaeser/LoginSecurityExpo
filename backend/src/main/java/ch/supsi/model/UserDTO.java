package ch.supsi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private int id;
    private String name;
    private String surname;
    private String username;
    private double salary;
    private UserRole role;

    public static UserDTO user2DTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .salary(user.getSalary())
                .role(user.getRole())
                .build();
    }
}
