package com.example.UberProject_BookingService.apis;

import com.example.UberProject_BookingService.DTO.DriverLocationResponseDto;
import com.example.UberProject_BookingService.DTO.NearByDriverRequestDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LocationServiceApi {

    @POST("api/v1/location/nearby/drivers")
    Call<DriverLocationResponseDto[]> getNearByDrivers (@Body NearByDriverRequestDto nearByDriverRequestDto);
}
