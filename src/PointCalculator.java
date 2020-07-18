import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointCalculator {

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

    private Point findPointBetweenTwoCoordinates(Point firstPoint, Point secondPoint, int coef) {
        int x = (int) (firstPoint.getX() + (coef * secondPoint.getX())) / (1 + coef);
        int y = (int) (firstPoint.getY() + (coef * secondPoint.getY())) / (1 + coef);
        return new Point(x, y);
    }
}
