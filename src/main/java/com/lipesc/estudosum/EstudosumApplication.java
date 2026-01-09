package com.lipesc.estudosum;

import java.util.*;
import java.util.stream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class EstudosumApplication implements CommandLineRunner {
		private static final Logger lg = LoggerFactory.getLogger(EstudosumApplication.class);

		
	public static void main(String[] args) {
		SpringApplication.run(EstudosumApplication.class, args);
	}
		private static JdbcTemplate jdbcTemplate;

		public EstudosumApplication(JdbcTemplate jdbcTemplate) {
				this.jdbcTemplate = jdbcTemplate;
		}

		@Override
		public void run(String... strings) throws Exception {
				lg.info("Teste logger");
				// jdbcTemplate.execute("DROP TABLE IF EXISTS customers");
				// jdbcTemplate.execute("CREATE TABLE customers(" +
				// 										 "id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255))");
				List<Object[]> addCustomers = Stream.of("felipe sampaio", "teste 1", "emacs fedora", "emacs java")
						.map(name -> name.split(" "))
						.collect(Collectors.toList());
						
				addCustomers.forEach(names -> lg.info("nomes listas {} {}", names[0], names[1]));

				jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", addCustomers);

				jdbcTemplate.query(
						"SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
            (rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name")), "emacs")
            .forEach(customer -> lg.info(customer.toString()));
				
		}
}
