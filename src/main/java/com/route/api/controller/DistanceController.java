package com.route.api.controller;

import com.route.api.service.api.RouteServiceApi;
import com.route.api.util.DistanceKmDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/distance")
@RequiredArgsConstructor
@Slf4j
public class DistanceController {

    private final RouteServiceApi routeService;

    @GetMapping("/inkm")
    public ResponseEntity<DistanceKmDto>calculateDistance(@RequestParam double lat1, @RequestParam double lon1,
                                                          @RequestParam double lat2, @RequestParam double lon2){
        double distanceInKm = routeService.distanceInKm(lat1, lon1, lat2, lon2);

        DistanceKmDto distanceKmDto = DistanceKmDto.builder()
                .startLatitude(lat1)
                .startLongitude(lon1)
                .endLatitude(lat2)
                .endLongitude(lon2)
                .distanceKm(distanceInKm)
                .dateRequest(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .build();

        return ResponseEntity.ok(distanceKmDto);
    }

}
