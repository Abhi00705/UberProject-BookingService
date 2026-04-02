package com.example.UberProject_BookingService.repositories;

import com.example.UberProject_EntityService.Enums.BookingStatus;
import com.example.UberProject_EntityService.modles.Booking;
import com.example.UberProject_EntityService.modles.Drivers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepositories extends JpaRepository<Booking, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Booking b SET b.bookingStatus=:status, b.drivers=:driver WHERE b.id=:id")
    public void UpdateBookingStatusAndDriverById(@Param("id") Long id, @Param("status") BookingStatus status, @Param("driver") Drivers driver);
}
