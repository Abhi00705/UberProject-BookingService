package com.example.UberProject_BookingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("com.example.UberProject_EntityService.modles")
public class UberProjectBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberProjectBookingServiceApplication.class, args);
	}

}
