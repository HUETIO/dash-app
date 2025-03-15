package com.example.dash_app.Service;
import com.example.dash_app.Entity.Supplier;
import com.example.dash_app.Interface.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Anotación clave para definir un servicio
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll(); // ✅ Usa el método de Spring Data JPA
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id); // ✅ Elimina por ID
    }
}