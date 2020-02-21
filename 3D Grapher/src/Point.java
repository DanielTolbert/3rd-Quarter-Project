public class Point implements Comparable{

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

    public Point setColorFromTime( double time ) {
        this.setColor(color + (int)time);
        return this;
    }

    @Override
    public int compareTo(Object o) {
        Point op = (Point)o;
        if (this.getX() > op.getX()) {
            return 1;
        } else if (this.getX() < op.getX()) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        Point op = (Point)o;
        return this.getX() == op.getX() &&
                this.getY() == op.getY() &&
                this.getZ() == op.getZ() &&
                this.getColor() == op.getColor() &&
                this.getPointRadius() == op.getPointRadius();

    }
}
