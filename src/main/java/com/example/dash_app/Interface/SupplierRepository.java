package com.example.dash_app.Interface;

import com.example.dash_app.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> { }
