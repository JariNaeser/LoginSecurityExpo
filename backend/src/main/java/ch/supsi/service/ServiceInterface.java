package ch.supsi.service;


import java.util.Optional;

public interface ServiceInterface<T, N> {
    Iterable<T> list();
    T post(T t);
    Optional<T> get(N id);
    T put(T t);
    void delete(N id);
    boolean exists(N id);
}


