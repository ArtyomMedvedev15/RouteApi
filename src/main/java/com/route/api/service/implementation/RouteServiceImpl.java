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

import java.util.HashMap;
import java.util.Map;

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

        if (!CoordinatesUtil.isValidLatitude(startLatitude) || !CoordinatesUtil.isValidLongitude(startLongitude) ||
                !CoordinatesUtil.isValidLatitude(endLatitude) || !CoordinatesUtil.isValidLongitude(endLongitude)) {
            throw new InvalidParameterException("Invalid coordinates. Please provide valid latitude and longitude values.");
        }

        double radLat1 = Math.toRadians(startLatitude);
        double radLon1 = Math.toRadians(startLongitude);
        double radLat2 = Math.toRadians(endLatitude);
        double radLon2 = Math.toRadians(endLongitude);

        double deltaLat = radLat2 - radLat1;
        double deltaLon = radLon2 - radLon1;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(radLat1) * Math.cos(radLat2) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    @Override
    public String routeOnRoadByJson(double startLatitude, double startLongitude, double endLatitude, double endLongitude) throws InvalidParameterException {
        if (!CoordinatesUtil.isValidLatitude(startLatitude) || !CoordinatesUtil.isValidLongitude(startLongitude) ||
                !CoordinatesUtil.isValidLatitude(endLatitude) || !CoordinatesUtil.isValidLongitude(endLongitude)) {
            throw new InvalidParameterException("Invalid coordinates. Please provide valid latitude and longitude values.");
        }
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
            return JsonFormatterUtil.jsonFormatter(responseEntity.getBody());
        } else {
            throw new RuntimeException("Error: " + responseEntity.getStatusCode());
        }

    }
}
