package com.example.dash_app.Controller;

import com.example.dash_app.Entity.Supplier;
import com.example.dash_app.Interface.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Component
public class SupplierConsolePrinter {
    @Autowired
    private SupplierRepository supplierRepository; // Inyectamos el repositorio

    private final RestTemplate restTemplate;
    private static final String SUPPLIERS_URL = "https://us-central1-puntored-dev.cloudfunctions.net/technicalTest-developer/api/getSuppliers";

    // Mejorado: Inyección por constructor más clara
    public SupplierConsolePrinter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void printSuppliers(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token.replace("Bearer ", ""));
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<List> response = restTemplate.exchange(
                    SUPPLIERS_URL,
                    HttpMethod.GET,
                    entity,
                    List.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                List<Map<String, Object>> suppliers = response.getBody();
                printSupplierList(suppliers);
            } else {
                System.err.println("Error al obtener proveedores. Código: " + response.getStatusCode());
            }

        } catch (Exception e) {
            System.err.println("Error crítico al obtener proveedores: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void printSupplierList(List<Map<String, Object>> suppliers) {
        System.out.println("\n=== Proveedores Disponibles ===");
        System.out.println("Total encontrados: " + suppliers.size());

        suppliers.forEach(supplier -> {
            try {
                String name = (String) supplier.getOrDefault("name", "N/A");
                Long code = supplier.get("id") != null ? Long.valueOf(supplier.get("id").toString()) : 0L;

                // Crear y guardar el proveedor
                Supplier supplierData = new Supplier();
                supplierData.setCode(code); // Asegúrate de que esta columna exista en la entidad Supplier
                supplierData.setName(name);

                supplierRepository.save(supplierData);

                System.out.printf("| %-15s | %-8s |\n", name, code);

            } catch (Exception e) {
                System.err.println("Error guardando proveedor: " + e.getMessage());
                e.printStackTrace();
            }
        });

        System.out.println("===============================");
    }

}