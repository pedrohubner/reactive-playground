package com.app.employee.services;

import com.app.employee.models.Employee;
import com.app.employee.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void create(Employee employee) {
        employeeRepository.save(employee).subscribe();
    }

    public Mono<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Flux<Employee> findNamedEmployeeThatStartsWithLetter(String letter) {
        return employeeRepository
                .findAll()
                .filter(employee -> employee.getName().startsWith(letter));
    }

    public Mono<Employee> update(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Mono<Void> delete(String id) {
        return employeeRepository.deleteById(id);
    }
}
