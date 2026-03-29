package com.example.UberProject_BookingService.repositories;

import com.example.UberProject_EntityService.modles.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepositories extends JpaRepository<Passenger, Long> {
}
