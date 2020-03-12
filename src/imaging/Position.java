package imaging;

import graphing.Point;

public class Position {

    // x > 0: right, x < 0: left, x = 0: centered (in meters)
    // y > 0: up, y < 0: down, y = 0: centered (in meters)
    // z: meters away from screen
    private float x, y, z;
    private int index;

    public Position(float x, float y, float z, int index) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.index = index;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public int getIndex() {
        return index;
    }

    public static Position fromPoint(Point p) {
        return new Position(p.getX(), p.getY(), p.getZ(), 1);
    }
}
