package com.example.UberProject_BookingService.controllers;

import com.example.UberProject_BookingService.DTO.CreateBookingRequestDto;
import com.example.UberProject_BookingService.DTO.CreateBookingResponseDto;
import com.example.UberProject_BookingService.DTO.UpdateBookingRequestDto;
import com.example.UberProject_BookingService.DTO.UpdateBookingResponseDto;
import com.example.UberProject_BookingService.services.BookingServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{bookingId}")
    public ResponseEntity<UpdateBookingResponseDto> updateBooking(@RequestBody UpdateBookingRequestDto requestDto, @PathVariable Long bookingId){

        return new ResponseEntity<>(bookingService.updateBooking(requestDto, bookingId), HttpStatus.OK);
    }
}
