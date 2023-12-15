package ch.supsi.service;

import ch.supsi.model.User;
import ch.supsi.model.UserRole;
import ch.supsi.repository.JPAUserRepository;
import ch.supsi.util.PasswordHelper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ServiceInterface<User, Integer>{
    private final JPAUserRepository userRepository;

    public UserService(JPAUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        if(userRepository.findAll().isEmpty()) {
            // Create initial users: Admin and user
            // For educational purposes only

            String adminSalt = PasswordHelper.getGeneratedSalt();
            String userSalt = PasswordHelper.getGeneratedSalt();

            User admin = new User();
            admin.setName("John");
            admin.setSurname("Doe");
            admin.setUsername("admin");
            admin.setPassword(PasswordHelper.encrypt("admin", adminSalt));
            admin.setSalt(adminSalt);
            admin.setSalary(12500.0);
            admin.setRole(UserRole.ROLE_ADMIN);

            User user = new User();
            user.setName("Paul");
            user.setSurname("Frank");
            user.setUsername("user");
            user.setPassword(PasswordHelper.encrypt("user", userSalt));
            user.setSalt(userSalt);
            user.setSalary(8000.0);
            user.setRole(UserRole.ROLE_USER);

            // Persist users
            userRepository.save(admin);
            userRepository.save(user);
        }
    }

    @Override
    public Iterable<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User post(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> get(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User put(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return userRepository.existsById(id);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username){
        return this.userRepository.findByUsername(username);
    }

}

