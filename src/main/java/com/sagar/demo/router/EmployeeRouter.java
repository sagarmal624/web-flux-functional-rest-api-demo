package com.sagar.demo.router;

import com.sagar.demo.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class EmployeeRouter {
    @Bean
    public RouterFunction<ServerResponse> routerEmployee(EmployeeService employeeService) {
        return RouterFunctions
                .route(POST("/emp/create"), employeeService::create)
                .andRoute(GET("emp/{id}"), employeeService::findById);
    }
}
