package com.example.UberProject_BookingService.DTO;

import com.example.UberProject_EntityService.modles.Drivers;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingResponseDto {
    private Long BookingId;
    private String status;
    private Drivers driver;
}
