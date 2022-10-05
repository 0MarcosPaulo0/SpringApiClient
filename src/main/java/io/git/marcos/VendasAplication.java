package io.git.marcos;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.git.marcos.domain.entity.Cliente;
import io.git.marcos.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasAplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando Clientes");
          clientes.salvar(new Cliente("Marcos"));
          clientes.salvar(new Cliente("Outro cliente"));

            System.out.println("Buscando clientes:");
          List<Cliente> todosClientes = clientes.obterTodos();
          todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes:");
          todosClientes.forEach(c -> {
              c.setNome(c.getNome() + " atualizado.");
              clientes.atualizar(c);
          });

            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando clientes por nome:");
          clientes.buscarPorNome("cli").forEach(System.out::println);


            System.out.println("Deletando Clientes:");
            clientes.obterTodos().forEach(c -> {
                clientes.deletar(c);
            });

          todosClientes = clientes.obterTodos();
          if(todosClientes.isEmpty()){
              System.out.println("Nenhum cliente encontrado");
          }else{
              todosClientes.forEach(System.out::println);
          }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasAplication.class, args);
    }
}
