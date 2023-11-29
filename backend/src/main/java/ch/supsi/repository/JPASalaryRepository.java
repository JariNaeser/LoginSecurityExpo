package ch.supsi.repository;

import ch.supsi.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPASalaryRepository extends JpaRepository<Salary, Integer> {}

