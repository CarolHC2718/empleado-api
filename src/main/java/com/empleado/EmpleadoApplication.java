package com.empleado;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.empleado")
public class EmpleadoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmpleadoApplication.class, args);
    }
}
