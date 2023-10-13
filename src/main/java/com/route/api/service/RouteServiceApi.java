package com.route.api.service;

public interface RouteServiceApi {
    double distanceInKm(double lat1, double lon1, double lat2, double lon2);
    String routeByJson(double lat1, double lon1, double lat2, double lon2);
}
