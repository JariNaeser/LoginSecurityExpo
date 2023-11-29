package ch.supsi.service;

import ch.supsi.model.Salary;
import ch.supsi.repository.JPASalaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService implements ServiceInterface<Salary, Integer>{

    private final JPASalaryRepository salaryRepository;

    public SalaryService(JPASalaryRepository salaryRepository){
        this.salaryRepository = salaryRepository;
    }

    @Override
    public Iterable<Salary> list() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary post(Salary Salary) {
        return salaryRepository.save(Salary);
    }

    @Override
    public Optional<Salary> get(Integer id) {
        return salaryRepository.findById(id);
    }

    @Override
    public Salary put(Salary Salary) {
        return salaryRepository.save(Salary);
    }

    @Override
    public void delete(Integer id) {
        salaryRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return salaryRepository.existsById(id);
    }

    public List<Salary> getAll(){
        return salaryRepository.findAll();
    }
}
