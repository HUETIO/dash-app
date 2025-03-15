package com.example.dash_app.Interface;

import com.example.dash_app.Model.RechargeCompleted;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RechargeRepository extends JpaRepository<RechargeCompleted, Long> {
}