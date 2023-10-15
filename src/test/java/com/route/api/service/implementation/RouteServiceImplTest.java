package com.route.api.service.implementation;

import com.route.api.exception.InvalidParameterException;
import com.route.api.service.api.RouteServiceApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RouteServiceImplTest {
    @Autowired
    private RouteServiceApi routeService;

    @Test
    void GetDistanceInKmTest_WithValidCoordinates_ReturnTrue() throws InvalidParameterException {
        double distanceInKm = routeService.distanceInKm(-74.0445004, 40.6892494, 2.2945111, 48.8582573);
        assertEquals(8506.77745427709,distanceInKm);
    }

    @Test
    void GetDistanceInKmTest_WithValidCoordinates2_ReturnTrue() throws InvalidParameterException {
        double distanceInKm = routeService.distanceInKm(52.231838, 21.005995, 52.247976, 21.015256);
        assertEquals(1.9020368211503094,distanceInKm);
    }
    @Test
    void GetDistanceInKmTest_WithInValidCoordinatesStartLatitude_ThrowException(){
        InvalidParameterException invalidParameterException = assertThrows(InvalidParameterException.class,
                () -> routeService.distanceInKm(1252.231838, 21.005995, 52.247976, 21.015256)
        );
        assertEquals("Invalid coordinates. Please provide valid latitude and longitude values.",invalidParameterException.getMessage());
    }

    @Test
    void GetDistanceInKmTest_WithInValidCoordinatesStartLongitude_ThrowException(){
        InvalidParameterException invalidParameterException = assertThrows(InvalidParameterException.class,
                () -> routeService.distanceInKm(52.231838, 1221.005995, 52.247976, 21.015256)
        );
        assertEquals("Invalid coordinates. Please provide valid latitude and longitude values.",invalidParameterException.getMessage());
    }

    @Test
    void GetDistanceInKmTest_WithInValidCoordinatesEndLatitude_ThrowException(){
        InvalidParameterException invalidParameterException = assertThrows(InvalidParameterException.class,
                () -> routeService.distanceInKm(52.231838, 21.005995, 1252.247976, 21.015256)
        );
        assertEquals("Invalid coordinates. Please provide valid latitude and longitude values.",invalidParameterException.getMessage());
    }
    @Test
    void GetDistanceInKmTest_WithInValidCoordinatesEndLongitude_ThrowException() {
        InvalidParameterException invalidParameterException = assertThrows(InvalidParameterException.class,
                () -> routeService.distanceInKm(52.231838, 21.005995, 52.247976, 1221.015256)
        );
        assertEquals("Invalid coordinates. Please provide valid latitude and longitude values.",invalidParameterException.getMessage());
    }
    @Test
    void RouteOnRoadByJsonTest_WithValidCoordinated_ReturnTrue() throws InvalidParameterException {
        String routeOnRoadJson = routeService.routeOnRoadByJson(52.231838, 21.005995, 52.247976, 21.015256);
        assertTrue(routeOnRoadJson.contains("bbox"));
    }

    @Test
    void GetDistanceInRoadTest_WithInValidCoordinatesStatLatitude_ThrowException() {
        InvalidParameterException invalidParameterException = assertThrows(InvalidParameterException.class,
                () -> routeService.routeOnRoadByJson(1252.231838, 21.005995, 52.247976, 21.015256)
        );
        assertEquals("Invalid coordinates. Please provide valid latitude and longitude values.",invalidParameterException.getMessage());
    }

    @Test
    void GetDistanceInRoadTest_WithInValidCoordinatesStatLongitude_ThrowException() {
        InvalidParameterException invalidParameterException = assertThrows(InvalidParameterException.class,
                () -> routeService.routeOnRoadByJson(52.231838, 1221.005995, 52.247976, 21.015256)
        );
        assertEquals("Invalid coordinates. Please provide valid latitude and longitude values.",invalidParameterException.getMessage());
    }

    @Test
    void GetDistanceInRoadTest_WithInValidCoordinatesEndLatitude_ThrowException() {
        InvalidParameterException invalidParameterException = assertThrows(InvalidParameterException.class,
                () -> routeService.routeOnRoadByJson(52.231838, 21.005995, 1252.247976, 21.015256)
        );
        assertEquals("Invalid coordinates. Please provide valid latitude and longitude values.",invalidParameterException.getMessage());
    }

    @Test
    void GetDistanceInRoadTest_WithInValidCoordinatesEndLongitude_ThrowException() {
        InvalidParameterException invalidParameterException = assertThrows(InvalidParameterException.class,
                () -> routeService.routeOnRoadByJson(52.231838, 21.005995, 52.247976, 1221.015256)
        );
        assertEquals("Invalid coordinates. Please provide valid latitude and longitude values.",invalidParameterException.getMessage());
    }
}