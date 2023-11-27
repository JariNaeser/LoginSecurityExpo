package ch.supsi.service;

import ch.supsi.model.User;
import ch.supsi.repository.JPAUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ServiceInterface<User, Integer>{
    private final JPAUserRepository userRepository;

    public UserService(JPAUserRepository userRepository){
        this.userRepository = userRepository;
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

}

