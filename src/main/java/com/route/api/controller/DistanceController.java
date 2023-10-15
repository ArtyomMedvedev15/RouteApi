package com.route.api.controller;

import com.route.api.exception.InvalidParameterException;
import com.route.api.service.api.RouteServiceApi;
import com.route.api.dto.DistanceKmDto;
import com.route.api.dto.DistanceRouteDto;
import com.route.api.util.LimitRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

    @LimitRequest(value = 10)
    @GetMapping("/inkm")
    public ResponseEntity<DistanceKmDto>calculateDistanceInKm(@RequestParam double startLatitude, @RequestParam double startLongitude,
                                                              @RequestParam double endLatitude, @RequestParam double endLongitude) throws InvalidParameterException {

        double distanceInKm = routeService.distanceInKm(startLatitude, startLongitude, endLatitude, endLongitude);


        DistanceKmDto distanceKmDto = DistanceKmDto.builder()
                .startLatitude(startLatitude)
                .startLongitude(startLongitude)
                .endLatitude(endLatitude)
                .endLongitude(endLongitude)
                .distanceKm(distanceInKm)
                .dateRequest(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .build();
        log.info("Get distance with {} with status 200 in {}",distanceKmDto,new Date());
        return ResponseEntity.ok(distanceKmDto);
    }

    @LimitRequest(value = 10)
    @GetMapping("/inroad")
    public ResponseEntity<DistanceRouteDto>distanceByRoad(@RequestParam double startLatitude, @RequestParam double startLongitude,
                                                          @RequestParam double endLatitude, @RequestParam double endLongitude) throws InvalidParameterException {
        String distanceInRoad = routeService.routeOnRoadByJson(startLatitude, startLongitude, endLatitude, endLongitude);

        DistanceRouteDto distanceRouteDto = DistanceRouteDto.builder()
                .startLatitude(startLatitude)
                .startLongitude(startLongitude)
                .endLatitude(endLatitude)
                .endLongitude(endLongitude)
                .route(distanceInRoad)
                .dateRequest(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .build();

        log.info("Get distance with {} with status 200 in {}",distanceRouteDto,new Date());
        return ResponseEntity.ok(distanceRouteDto);
    }


}
