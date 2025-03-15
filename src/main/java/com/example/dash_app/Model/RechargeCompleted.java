package com.example.dash_app.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recargas_realizadas")
public class RechargeCompleted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String proveedor;
    private Double monto;
    private LocalDateTime fechaRecarga;
    private Double telefono;  // Nuevo campo

    public RechargeCompleted() {
        this.fechaRecarga = LocalDateTime.now(); // Guarda la fecha automáticamente
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public LocalDateTime getFechaRecarga() { return fechaRecarga; }
    public void setFechaRecarga(LocalDateTime fechaRecarga) { this.fechaRecarga = fechaRecarga; }

    public Double getTelefono() { return telefono; }  // Getter para el nuevo campo
    public void setTelefono(double telefono) { this.telefono = Double.valueOf(String.valueOf(telefono)); }  // Setter para el nuevo campo


}