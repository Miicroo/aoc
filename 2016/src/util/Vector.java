package util;

public class Vector {
    private String direction;
    private int length;

    public Vector(String direction, int length) {
        this.direction = direction;
        this.length = length;
    }

    public String getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return direction + length;
    }
}
