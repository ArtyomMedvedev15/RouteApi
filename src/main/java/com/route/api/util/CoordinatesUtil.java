package com.route.api.util;

import com.route.api.exception.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class CoordinatesUtil {
    public static boolean isValidLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

    public static boolean isValidLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    public static void ValidationCoordinates(double startLatitude, double startLongitude, double endLatitude, double endLongitude) throws InvalidParameterException {
        if (!CoordinatesUtil.isValidLatitude(startLatitude) || !CoordinatesUtil.isValidLongitude(startLongitude) ||
                !CoordinatesUtil.isValidLatitude(endLatitude) || !CoordinatesUtil.isValidLongitude(endLongitude)) {
            InvalidParameterException invalidParameterException =
                    new InvalidParameterException("Invalid coordinates. Please provide valid latitude and longitude values.");
            log.error("Error with message: {} in {}",invalidParameterException.getMessage(),new Date());
            throw invalidParameterException;
        }
    }
}
