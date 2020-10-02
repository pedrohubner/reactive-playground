package com.app.facade;

import com.app.employee.models.Employee;
import com.app.employee.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class AppFacade {

    private final EmployeeService employeeService;

    public void createEmployee(Employee employee) {
        employeeService.create(employee);
    }

    public Mono<Employee> findEmployeeById(String id) {
        return employeeService.findById(id);
    }

    public Flux<Employee> findAll() {
        return employeeService.findAll();
    }

    public Flux<Employee> findNamedEmployeeThatStartsWithLetter(String letter) {
        return employeeService.findNamedEmployeeThatStartsWithLetter(letter);
    }

    public Mono<Employee> updateEmployee(Employee employee) {
        return employeeService.update(employee);
    }

    public Mono<Void> deleteEmployee(String id) {
        return employeeService.delete(id);
    }
}
