package com.route.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DistanceRouteDto {
    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
    private String route;
    private String dateRequest;
}
