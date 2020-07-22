import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointCalculator {

    public List<Point> calculateDragonCurvePoints(List<Point> fractalPoints, int iterations, int degree) {
        List<Point> temporaryList = new ArrayList<>(List.copyOf(fractalPoints));
        if (fractalPoints.size() >= 2) {
            for (int i = 0; i < iterations; i++) {
                Point centralPoint = fractalPoints.get(fractalPoints.size() - 1);
                for (int j = fractalPoints.size() - 2; j >= 0; j--) {
                    temporaryList.add(rotatePointCoordinatesOn(fractalPoints.get(j), degree, centralPoint));
                }
                fractalPoints = temporaryList;
            }
        } else {
            System.out.println("Figure does`nt have top points");
        }
        return fractalPoints;
    }

    public List<Point> calculateChaosGameFractalPoints(List<Point> figureTops, int iterations, Point currentPoint) {
        List<Point> result = new ArrayList<>();
        if (figureTops.size() != 0) {
            Random random = new Random();
            for (int i = 0; i < iterations; i++) {
                int randomInt = random.nextInt(figureTops.size());
                currentPoint = findPointBetweenTwoCoordinates(currentPoint, figureTops.get(randomInt), 2);
                result.add(currentPoint);
            }
        } else {
            System.out.println("Figure does`nt have top points");
        }
        return result;
    }

    private Point findPointBetweenTwoCoordinates(Point firstPoint, Point secondPoint, int coefficient) {
        int x = (int) (firstPoint.getX() + (coefficient * secondPoint.getX())) / (1 + coefficient);
        int y = (int) (firstPoint.getY() + (coefficient * secondPoint.getY())) / (1 + coefficient);
        return new Point(x, y);
    }

    protected Point rotatePointCoordinatesOn(Point currentPoint, int degree, Point centralPoint) {
        int x0 = (int) centralPoint.getX();
        int y0 = (int) centralPoint.getY();
        int x = (int) currentPoint.getX();
        int y = (int) currentPoint.getY();
        int differenceBetweenX = x - x0;
        int differenceBetweenY = y - y0;
        double cosDegree = Math.cos(Math.toRadians(degree));
        double sinDegree = Math.sin(Math.toRadians(degree));
        x = x0 + (int) (differenceBetweenX * cosDegree) - (int) (differenceBetweenY * sinDegree);
        y = y0 + (int) (differenceBetweenY * cosDegree) + (int) (differenceBetweenX * sinDegree);
        return new Point(x, y);
    }
}
