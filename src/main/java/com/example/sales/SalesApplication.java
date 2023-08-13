package com.example.sales;

import com.example.sales.entities.Sales;
import com.example.sales.repositories.SalesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
@SpringBootApplication
public class SalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(SalesRepository salesRepository){

		return args -> {
		//salesRepository.save(new Sales(null,"Jessica Lam","Washing Machine", 4000.00, new Date()));
		//salesRepository.save(new Sales(null,"Janna San","Music System", 3000.00, new Date()));

			salesRepository.findAll().forEach(p->{
		//		System.out.println(p.getName());
			});
		};
	}
}
