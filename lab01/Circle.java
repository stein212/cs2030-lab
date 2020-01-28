public class Circle {
    private Point center;
    private double radius;

    private Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public static Circle getCircle(Point center, double radius) {
        if (radius <= 0)
            return null;

        return new Circle(center, radius);
    }

    @Override
    public String toString() {
        return String.format("circle of radius %.1f centered at %s", radius, center);
    }

    public boolean containsPoint(Point point) {
        return point.x >= center.x - radius
            && point.x <= center.x + radius
            && point.y >= center.y - radius
            && point.y <= center.y + radius;
    }
}           
