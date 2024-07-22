package com.example.food_basket_optimization.geolocation;

public class GeolocationUtil {

    private static final double EARTH_RADIUS = 6371.3;

    private static double toRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    public static Double calculateDistanceBetweenPoints(double lat1, double lon1, double lat2, double lon2) {
        lat1 = toRadians(lat1);
        lon1 = toRadians(lon1);
        lat2 = toRadians(lat2);
        lon2 = toRadians(lon2);

        // Разница координат
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        // Формула Хаверсина
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Расстояние
        return EARTH_RADIUS * c;

    }
}
