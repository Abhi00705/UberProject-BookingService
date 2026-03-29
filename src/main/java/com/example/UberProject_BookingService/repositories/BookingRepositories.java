package com.example.UberProject_BookingService.repositories;

import com.example.UberProject_EntityService.modles.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepositories extends JpaRepository<Booking, Long> {
}
