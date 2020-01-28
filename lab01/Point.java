public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("point (%.3f, %.3f)", x, y);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;

        if (this.getClass() != other.getClass())
            return false;

        Point otherPoint = (Point)other;

        if (otherPoint.x == x && otherPoint.y == y)
            return true;

        return false;
    }

    public double distanceTo(Point otherPoint) {
        return Math.sqrt(Math.pow(otherPoint.x - x, 2) + Math.pow(otherPoint.y - y, 2));
    }

    public Point midPoint(Point otherPoint) {
        return new Point((x + otherPoint.x)/2, (y + otherPoint.y)/2);
    }

    public double angleTo(Point otherPoint) {
        return Math.atan2(otherPoint.y - y, otherPoint.x - x);
    }

    public Point moveTo(double angle, double distance) {
        return new Point(x + distance * Math.cos(angle), y + distance * Math.sin(angle));
    }
}
