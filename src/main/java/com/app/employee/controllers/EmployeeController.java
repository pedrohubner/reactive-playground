package com.app.employee.controllers;

import com.app.employee.models.Employee;
import com.app.facade.AppFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final AppFacade appFacade;

    @PostMapping(value = {"/"})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Employee employee) {
        appFacade.createEmployee(employee);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<Employee>> findById(@PathVariable String id) {
        Mono<Employee> employee = appFacade.findEmployeeById(id);
        HttpStatus status = employee != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(employee, status);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> findAll() {
        return appFacade.findAll();
    }

    @GetMapping("/user/{letter}")
    public Flux<Employee> findNamedEmployeeThatStartsWithLetter(@PathVariable String letter) {
        return appFacade.findNamedEmployeeThatStartsWithLetter(letter);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee employee) {
        return appFacade.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        appFacade.deleteEmployee(id).subscribe();
    }
}
