package com.example.UberProject_BookingService.repositories;

import com.example.UberProject_EntityService.modles.Drivers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Drivers, Long> {
}
