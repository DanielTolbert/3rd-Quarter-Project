package imaging;

import graphing.Point;

public class Position {

    // x > 0: right, x < 0: left, x = 0: centered (in meters)
    // y > 0: up, y < 0: down, y = 0: centered (in meters)
    // z: meters away from screen
    private int x, y, z;

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public static Position fromPoint(Point p) {
        return new Position(p.getX(), p.getY(), p.getZ());
    }
}
