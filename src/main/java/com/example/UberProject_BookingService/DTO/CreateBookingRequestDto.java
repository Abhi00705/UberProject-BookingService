package com.example.UberProject_BookingService.DTO;

import com.example.UberProject_EntityService.Enums.BookingStatus;
import com.example.UberProject_EntityService.modles.Drivers;
import com.example.UberProject_EntityService.modles.ExactLocation;
import com.example.UberProject_EntityService.modles.Passenger;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookingRequestDto {

    private Long passengerId;

    private ExactLocation startLocation;

    private ExactLocation endLocation;
}
