package com.example.dash_app.Controller;

import com.example.dash_app.Model.RechargeCompleted;
import com.example.dash_app.Interface.RechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.lang.Double.*;

@RestController
@RequestMapping("/api/recargas")
@CrossOrigin(originPatterns = "*", allowCredentials = "true") // Usa originPatterns en lugar de origins
public class RechargeController {

    @Autowired
    private RechargeRepository rechargeRepository;

    @PostMapping("/guardar")
    public RechargeCompleted guardarRecarga(@RequestBody Map<String, Object> request) {
        RechargeCompleted recarga = new RechargeCompleted();
        recarga.setProveedor((String) request.get("proveedor"));
        recarga.setMonto(parseDouble(request.get("monto").toString()));
        recarga.setTelefono(parseDouble(request.get("telefono").toString()));
        recarga.setFechaRecarga(LocalDateTime.now());

        return rechargeRepository.save(recarga);
    }

    @GetMapping("/transacciones") // Nuevo endpoint para obtener transacciones
    public List<RechargeCompleted> obtenerTransacciones() {
        return rechargeRepository.findAll(); // Obtiene todas las recargas de la base de datos
    }
}