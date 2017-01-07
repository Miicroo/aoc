package util;

public class Position {
    private long y, x;

    public Position(long y, long x) {
        this.y = y;
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public long getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (y != position.y) return false;
        return x == position.x;

    }

    @Override
    public int hashCode() {
        int result = (int) (y ^ (y >>> 32));
        result = 31 * result + (int) (x ^ (x >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
