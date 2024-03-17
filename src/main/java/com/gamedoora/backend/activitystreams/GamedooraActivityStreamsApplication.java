package com.gamedoora.backend.activitystreams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.gamedoora.model.*" , "com.gamedoora.backend.*"} , exclude = {DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
@EntityScan("com.gamedoora.model.dao")
public class GamedooraActivityStreamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamedooraActivityStreamsApplication.class, args);
	}

}
