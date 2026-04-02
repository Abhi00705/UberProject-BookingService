package com.example.UberProject_BookingService.DTO;

import com.example.UberProject_EntityService.Enums.BookingStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingRequestDto {
    private BookingStatus status;
    private Long driverId;
}
