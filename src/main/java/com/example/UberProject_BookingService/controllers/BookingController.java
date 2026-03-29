package com.example.UberProject_BookingService.controllers;

import com.example.UberProject_BookingService.DTO.CreateBookingRequestDto;
import com.example.UberProject_BookingService.DTO.CreateBookingResponseDto;
import com.example.UberProject_BookingService.services.BookingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    private final BookingServiceImpl bookingService;
    public BookingController(BookingServiceImpl bookingService){
        this.bookingService=bookingService;
    }
    @GetMapping
    public ResponseEntity<CreateBookingResponseDto> createBooking(@RequestBody CreateBookingRequestDto createBookingRequestDto){
        return new ResponseEntity<>(bookingService.createBooking(createBookingRequestDto), HttpStatus.CREATED);
    }
}
