public class Point {

    private int x;
    private int y;
    private int z;
    private int pointRadius;
    private int color;

    public Point(int x, int y, int z, int pointRadius, int color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pointRadius = pointRadius;
        this.color = color;
    }

    public Point(int x, int y, int z, int color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getPointRadius() {
        return pointRadius;
    }

    public void setPointRadius(int pointRadius) {
        this.pointRadius = pointRadius;
    }
}
