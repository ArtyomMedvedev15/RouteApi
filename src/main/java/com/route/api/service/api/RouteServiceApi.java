package com.route.api.service.api;

public interface RouteServiceApi {
    double distanceInKm(double lat1, double lon1, double lat2, double lon2);
    String routeOnRoadByJson(double lat1, double lon1, double lat2, double lon2);
}
