package com.route.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class DistanceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final String BASE_URL = "/api/v1/distance";


    @Test
    void CalculateDistanceInKmTest_WithValidCoordinates_ReturnTrue() throws Exception {
        mockMvc.perform(get(BASE_URL + "/inkm?startLatitude=-74.0445004&startLongitude=40.6892494&endLatitude=2.2945111&endLongitude=48.8582573")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.distanceKm").value(8506.77745427709));
    }

    @Test
    void CalculateDistanceInKmTest_WithInValidCoordinatesStartLatitude_ThrowException() throws Exception {
        mockMvc.perform(get(BASE_URL + "/inkm?startLatitude=-1274.0445004&startLongitude=40.6892494&endLatitude=2.2945111&endLongitude=48.8582573")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_message").value("Invalid coordinates. Please provide valid latitude and longitude values."));
    }

    @Test
    void CalculateDistanceInKmTest_WithInValidCoordinatesStartLongitude_ThrowException() throws Exception {
        mockMvc.perform(get(BASE_URL + "/inkm?startLatitude=-74.0445004&startLongitude=1240.6892494&endLatitude=2.2945111&endLongitude=48.8582573")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_message").value("Invalid coordinates. Please provide valid latitude and longitude values."));
    }

    @Test
    void CalculateDistanceInKmTest_WithInValidCoordinatesEndLatitude_ThrowException() throws Exception {
        mockMvc.perform(get(BASE_URL + "/inkm?startLatitude=-74.0445004&startLongitude=40.6892494&endLatitude=1232.2945111&endLongitude=48.8582573")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_message").value("Invalid coordinates. Please provide valid latitude and longitude values."));
    }

    @Test
    void CalculateDistanceInKmTest_WithInValidCoordinatesEndLongitude_ThrowException() throws Exception {
        mockMvc.perform(get(BASE_URL + "/inkm?startLatitude=-74.0445004&startLongitude=40.6892494&endLatitude=2.2945111&endLongitude=1248.8582573")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_message").value("Invalid coordinates. Please provide valid latitude and longitude values."));
    }

    @Test
    void DistanceByRoadTest_WothValidCoordinates_ReturnTrue() throws Exception {
        mockMvc.perform(get(BASE_URL + "/inroad?startLatitude=52.231838&startLongitude=21.005995&endLatitude=52.247976&endLongitude=21.015256")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.route").exists());
    }

    @Test
    void DistanceByRoadTest_WothInValidCoordinatesStartLatitude_ThrowException() throws Exception {
        mockMvc.perform(get(BASE_URL + "/inroad?startLatitude=1252.231838&startLongitude=21.005995&endLatitude=52.247976&endLongitude=21.015256")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_message").value("Invalid coordinates. Please provide valid latitude and longitude values."));
    }

    @Test
    void DistanceByRoadTest_WothInValidCoordinatesStartLongitude_ThrowException() throws Exception {
        mockMvc.perform(get(BASE_URL + "/inroad?startLatitude=52.231838&startLongitude=1221.005995&endLatitude=52.247976&endLongitude=21.015256")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_message").value("Invalid coordinates. Please provide valid latitude and longitude values."));
    }

    @Test
    void DistanceByRoadTest_WothInValidCoordinatesEndLatitude_ThrowException() throws Exception {
        mockMvc.perform(get(BASE_URL + "/inroad?startLatitude=52.231838&startLongitude=21.005995&endLatitude=1252.247976&endLongitude=21.015256")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_message").value("Invalid coordinates. Please provide valid latitude and longitude values."));
    }

    @Test
    void DistanceByRoadTest_WothInValidCoordinatesEndLongitude_ThrowException() throws Exception {
        mockMvc.perform(get(BASE_URL + "/inroad?startLatitude=52.231838&startLongitude=21.005995&endLatitude=52.247976&endLongitude=1221.015256")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_message").value("Invalid coordinates. Please provide valid latitude and longitude values."));
    }
}