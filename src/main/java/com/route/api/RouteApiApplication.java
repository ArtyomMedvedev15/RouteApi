package com.route.api;

import com.route.api.service.api.RouteServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RouteApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouteApiApplication.class, args);
    }

}
