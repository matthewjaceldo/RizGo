package utils;

public class MapRequest {

    private static String requestedLocation;

    private MapRequest() {
    }

    public static void setRequestedLocation(String location) {
        requestedLocation = location;
    }

    public static String getRequestedLocation() {
        return requestedLocation;
    }

    public static boolean hasRequest() {
        return requestedLocation != null
                && !requestedLocation.isBlank();
    }

    public static void clear() {
        requestedLocation = null;
    }
}