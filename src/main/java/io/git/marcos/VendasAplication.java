package io.git.marcos;

import io.git.marcos.domain.entity.Cliente;
import io.git.marcos.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasAplication {

    public static void main(String[] args) {
        SpringApplication.run(VendasAplication.class, args);
    }
}
