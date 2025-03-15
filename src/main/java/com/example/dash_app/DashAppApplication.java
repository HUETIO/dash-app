package com.example.dash_app;

import com.example.dash_app.Controller.SupplierConsolePrinter;
import com.example.dash_app.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DashAppApplication {

	private final AuthService authService;

	@Autowired
	private SupplierConsolePrinter supplierConsolePrinter;

	public DashAppApplication(AuthService authService) {
		this.authService = authService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DashAppApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		String token = authService.authenticate();
		if (token != null) {
			System.out.println("TOKEN: " + token);
			supplierConsolePrinter.printSuppliers(token);
		}
	}
}