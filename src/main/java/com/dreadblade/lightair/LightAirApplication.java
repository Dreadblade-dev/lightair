package com.dreadblade.lightair;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LightAirApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightAirApplication.class, args);
		Sentry.captureMessage("Starting LightAir application!");
	}

}
