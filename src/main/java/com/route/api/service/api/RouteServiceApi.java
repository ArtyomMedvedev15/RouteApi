package com.route.api.service.api;

import com.route.api.exception.InvalidParameterException;
import org.springframework.web.bind.annotation.RequestParam;

public interface RouteServiceApi {
    double distanceInKm( double startLatitude, double startLongitude, double endLatitude, double endLongitude) throws InvalidParameterException;
    String routeOnRoadByJson(double startLatitude, double startLongitude, double endLatitude, double endLongitude) throws InvalidParameterException;
}
