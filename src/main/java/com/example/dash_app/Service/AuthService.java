package com.example.dash_app.Service;
import com.example.dash_app.Entity.Supplier;
import com.example.dash_app.Interface.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    @Autowired
    private SupplierRepository supplierRepository;

    public AuthService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    // Método para autenticarse y obtener el token Bearer
    public String authenticate() {
        String url = "https://us-central1-puntored-dev.cloudfunctions.net/technicalTest-developer/api/auth";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", "mtrQF6Q11eosqyQnkMY0JGFbGqcxVg5icvfVnX1ifIyWDvwGApJ8WUM8nHVrdSkN");
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = Map.of(
                "user", "user0147",
                "password", "#3Q34Sh0NlDS"
        );

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return (String) response.getBody().get("token");
        }
        return null;
    }

    // Método para obtener la lista de proveedores
    public List<Map<String, String>> getSuppliers(String token) {
        String url = "https://us-central1-puntored-dev.cloudfunctions.net/technicalTest-developer/api/getSuppliers";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);

        return response.getBody();
    }

    // Método para guardar proveedores
    public void saveSuppliers(String token) {
        List<Map<String, String>> suppliers = getSuppliers(token);

        for (Map<String, String> data : suppliers) {
            Supplier supplier = new Supplier();
            supplier.setName((String) data.get("name"));
            supplier.setCode(Long.valueOf((String) data.get("code")));
            supplierRepository.save(supplier);
        }
    }

}
