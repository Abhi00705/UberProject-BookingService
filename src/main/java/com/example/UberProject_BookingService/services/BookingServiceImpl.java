package com.example.UberProject_BookingService.services;

import com.example.UberProject_BookingService.DTO.*;
import com.example.UberProject_BookingService.apis.LocationServiceApi;
import com.example.UberProject_BookingService.repositories.BookingRepositories;
import com.example.UberProject_BookingService.repositories.DriverRepository;
import com.example.UberProject_BookingService.repositories.PassengerRepositories;
import com.example.UberProject_EntityService.Enums.BookingStatus;
import com.example.UberProject_EntityService.modles.Booking;
import com.example.UberProject_EntityService.modles.Drivers;
import com.example.UberProject_EntityService.modles.Passenger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{
    private DriverRepository driverRepository;
    private LocationServiceApi locationServiceApi;
    private final String LOCATION_SERVICE="http://localhost:7476";
    private final RestTemplate restTemplate;
    private final PassengerRepositories passengerRepositories;
    private final BookingRepositories bookingRepositories;

    public BookingServiceImpl(PassengerRepositories passengerRepositories,
                              BookingRepositories bookingRepositories,
                              LocationServiceApi locationServiceApi,
                              DriverRepository driverRepository) {
        this.passengerRepositories = passengerRepositories;
        this.bookingRepositories = bookingRepositories;
        this.restTemplate=new RestTemplate();
        this.locationServiceApi = locationServiceApi;
        this.driverRepository=driverRepository;
    }

    @Override
    public CreateBookingResponseDto createBooking(CreateBookingRequestDto createBookingRequestDto) {
        Passenger passenger = passengerRepositories
                .findById(createBookingRequestDto.getPassengerId())
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        System.out.println("---------pssenger: "+createBookingRequestDto.getPassengerId());
        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.ASSIGNING_DRIVER)
                .startLocation(createBookingRequestDto.getStartLocation())
                .endLocation(createBookingRequestDto.getEndLocation())
                .passenger(passenger)
                .build();
        Booking newBooking = bookingRepositories.save(booking);

        NearByDriverRequestDto nearByDrivers = NearByDriverRequestDto.builder()
                .latitude(createBookingRequestDto.getStartLocation().getLatitude())
                .longitude(createBookingRequestDto.getEndLocation().getLongitude())
                .build();

        processNearByDriversAsync(nearByDrivers);

//        ResponseEntity<DriverLocationResponseDto[]> listOfDrivers = restTemplate.postForEntity(LOCATION_SERVICE+"/api/v1/location/nearby/drivers", nearByDrivers, DriverLocationResponseDto[].class);


//        if(listOfDrivers.getStatusCode().is2xxSuccessful() && listOfDrivers.getBody()!= null)
//        {
//            List<DriverLocationResponseDto> driverLocations = Arrays.asList(listOfDrivers.getBody());
//            driverLocations.forEach(driverLocationResponseDto -> {
//                System.out.println(driverLocationResponseDto.getDriverId() + " " + "lat: " + driverLocationResponseDto.getLatitude() + " " + "lon: " + driverLocationResponseDto.getLongitude());
//            });
//        }

        return CreateBookingResponseDto.builder()
                .BookingId(newBooking.getId())
                .bookingStatus(newBooking.getBookingStatus().toString())
//                .drivers(newBooking.getDrivers())
                .build();
    }
     private void processNearByDriversAsync(NearByDriverRequestDto requestDto){
         Call<DriverLocationResponseDto[]> call = locationServiceApi.getNearByDrivers(requestDto);
         call.enqueue(new Callback<DriverLocationResponseDto[]>(){
             @Override
            public void onResponse(Call<DriverLocationResponseDto[]> call, Response<DriverLocationResponseDto[]> response){
                 if(response.isSuccessful() && response.body()!= null)
                 {
                     List<DriverLocationResponseDto> driverLocations = Arrays.asList(response.body());
                     driverLocations.forEach(driverLocationResponseDto -> {
                         System.out.println(driverLocationResponseDto.getDriverId()
                                 + " " + "lat: " + driverLocationResponseDto.getLatitude()
                                 + " " + "lon: " + driverLocationResponseDto.getLongitude());
                     });
                 }
                 else{
                     System.out.println("Response fail: "+response.message());
                 }
             }

             @Override
             public void onFailure(Call<DriverLocationResponseDto[]> call, Throwable throwable) {
                 throwable.printStackTrace();

             }
         });
     }

    @Override
    public UpdateBookingResponseDto updateBooking(UpdateBookingRequestDto requestDto, Long bookingId) {
        System.out.println("++++++++++++++++update booking - 111+++++++++++++++++");
        Drivers driver = driverRepository.findById(requestDto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        System.out.println("driver: "+driver.getId());
        if(driver != null){
            bookingRepositories.UpdateBookingStatusAndDriverById(bookingId, requestDto.getStatus(), driver);
        }
        System.out.println("---------update Booking - 118-----------");
        Optional<Booking> booking = bookingRepositories.findById(bookingId);

        return UpdateBookingResponseDto.builder()
                .BookingId(booking.get().getId())
                .status(booking.get().getBookingStatus().toString())
                .driver(booking.get().getDrivers())
                .build();
    }
}
