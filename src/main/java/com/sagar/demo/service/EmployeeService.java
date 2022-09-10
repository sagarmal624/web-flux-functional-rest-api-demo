package com.sagar.demo.service;

import com.sagar.demo.model.Employee;
import com.sagar.demo.repository.EmployeeRepository;
import com.sagar.demo.util.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        Mono<Employee> employeeMono = serverRequest.bodyToMono(Employee.class);
        ResponseDto responseDto = ResponseDto.builder().status(true).message("Employee Created Successfully").build();
        return employeeMono
                .flatMap(employeeRepository::save)
                .flatMap(dbEmp -> ServerResponse.accepted().contentType(MediaType.APPLICATION_JSON).body(fromValue(responseDto)))
                .switchIfEmpty(ServerResponse.badRequest().body(fromValue(ResponseDto.builder().status(false).message("Server can't process this data").build())));
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<Employee> mono = employeeRepository.findById(id);
        return mono
                .flatMap(emp -> ServerResponse.ok().body(fromValue(emp)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
