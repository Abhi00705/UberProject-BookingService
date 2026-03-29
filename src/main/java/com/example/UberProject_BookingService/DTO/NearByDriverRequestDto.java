package com.example.UberProject_BookingService.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearByDriverRequestDto {
    double latitude;
    double longitude;
}
