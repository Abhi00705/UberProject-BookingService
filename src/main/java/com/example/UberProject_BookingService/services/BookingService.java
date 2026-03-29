package com.example.UberProject_BookingService.services;

import com.example.UberProject_BookingService.DTO.CreateBookingRequestDto;
import com.example.UberProject_BookingService.DTO.CreateBookingResponseDto;
import com.example.UberProject_EntityService.modles.Booking;

public interface BookingService  {
    public CreateBookingResponseDto createBooking(CreateBookingRequestDto createBookingRequestDto);
}
