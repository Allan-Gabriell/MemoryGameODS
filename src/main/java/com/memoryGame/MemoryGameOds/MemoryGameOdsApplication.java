package com.memoryGame.MemoryGameOds;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // Depois excluir quando for mexer com os bancos
public class MemoryGameOdsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MemoryGameOdsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World!");
	}
}
