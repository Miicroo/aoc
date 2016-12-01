package util;

public class Position {
    private long longitude, latitude;

    public Position(long longitude, long latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (longitude != position.longitude) return false;
        return latitude == position.latitude;

    }

    @Override
    public int hashCode() {
        int result = (int) (longitude ^ (longitude >>> 32));
        result = 31 * result + (int) (latitude ^ (latitude >>> 32));
        return result;
    }
}
