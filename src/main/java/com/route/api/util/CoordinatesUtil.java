package com.route.api.util;

public class CoordinatesUtil {
    public static boolean isValidLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

    public static boolean isValidLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }
}
