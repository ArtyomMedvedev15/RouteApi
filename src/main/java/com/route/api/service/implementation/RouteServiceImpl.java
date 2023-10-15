package com.route.api.service.implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.route.api.exception.InvalidParameterException;
import com.route.api.service.api.RouteServiceApi;
import com.route.api.util.CoordinatesUtil;
import com.route.api.util.JsonFormatterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.route.api.util.CoordinatesUtil.ValidationCoordinates;

@Service
@Slf4j
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteServiceApi {
    private static final double EARTH_RADIUS_KM = 6371.0;
    @Value("${openrouteservice.api.key}") // Значение API-ключа из application.properties
    private String apiKey;
    private RestTemplate restTemplate;

    @Autowired
    public RouteServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public double distanceInKm(double startLatitude, double startLongitude, double endLatitude, double endLongitude) throws InvalidParameterException {
        ValidationCoordinates(startLatitude, startLongitude, endLatitude, endLongitude);
        double radStartLatitude = Math.toRadians(startLatitude);
        double radStartLongitude = Math.toRadians(startLongitude);
        double radEndLatitude = Math.toRadians(endLatitude);
        double radEndLongitude = Math.toRadians(endLongitude);

        double deltatLatitude = radEndLatitude - radStartLatitude;
        double deltaLongitude = radEndLongitude - radStartLongitude;

        double haversineA = Math.sin(deltatLatitude / 2) * Math.sin(deltatLatitude / 2) +
                Math.cos(radStartLatitude) * Math.cos(radEndLatitude) *
                        Math.sin(deltaLongitude / 2) * Math.sin(deltaLongitude / 2);

        double distanceC = 2 * Math.atan2(Math.sqrt(haversineA), Math.sqrt(1 - haversineA));
        log.info("Get distance in km from start position [{},{}] to end position [{},{}] in {}",startLatitude,startLongitude,endLatitude,endLongitude,new Date());
        return EARTH_RADIUS_KM * distanceC;
    }

    @Override
    public String routeOnRoadByJson(double startLatitude, double startLongitude, double endLatitude, double endLongitude) throws InvalidParameterException {
        ValidationCoordinates(startLatitude, startLongitude, endLatitude, endLongitude);
        String apiUrl = "https://api.openrouteservice.org/v2/directions/driving-car/json";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("coordinates", new double[][]{{startLongitude, startLatitude}, {endLongitude, endLatitude}});
        requestBody.put("format", "json");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("Get distance in km from start position [{},{}] to end position [{},{}] in {}",startLatitude,startLongitude,endLatitude,endLongitude,new Date());
            return JsonFormatterUtil.jsonFormatter(responseEntity.getBody());
        } else {
            RuntimeException runtimeException = new RuntimeException("Error: " + responseEntity.getStatusCode());
            log.error("Error with message: {} in {}",runtimeException.getMessage(),new Date());
            throw runtimeException;
        }

    }


}
