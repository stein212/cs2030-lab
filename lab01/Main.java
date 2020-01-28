import java.util.Scanner;

public class Main {
    public static void main(String ...args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfPoints = scanner.nextInt();

        Point[] points = new Point[numberOfPoints];

        // populate the points
        for (int i = 0; i < numberOfPoints; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();

            points[i] = new Point(x, y);
        }

        int maxPoints = 0;

        for (int i = 0; i < numberOfPoints; i++) {
            Point p1 = points[i];
            for (int j = 0; j < numberOfPoints; j++) {
                if (i == j)
                    continue;
         
                Point p2 = points[j];

                Circle circle = createCircle(p1, p2, 1);

                if (circle == null)
                    continue;

                int numberOfPointsInCircle = getNumberOfPointsInCircle(circle, points);

                if (numberOfPointsInCircle > maxPoints)
                    maxPoints = numberOfPointsInCircle;
            }
        }

        System.out.println(String.format("Maximum Disc Coverage: %d", maxPoints));
    }

    public static Circle createCircle(Point p1, Point p2, double radius) {
        if (p1.equals(p2) || p1.distanceTo(p2) > radius * 2)
            return null;

        Point midPoint = p1.midPoint(p2);
        double mc = Math.sqrt(Math.pow(radius, 2) - Math.pow(midPoint.distanceTo(p2), 2));
        double angleToC = midPoint.angleTo(p2) + Math.PI / 2;
        Point c = midPoint.moveTo(angleToC, mc);

        return Circle.getCircle(c, radius);
    }

    public static int getNumberOfPointsInCircle(Circle circle, Point[] points) {
        int numberOfPoints = 0;
        for (int i = 0; i < points.length; i++) {
            Point point = points[i];

            if (circle.containsPoint(point))
                numberOfPoints++;
        }

        return numberOfPoints;
    }
}
