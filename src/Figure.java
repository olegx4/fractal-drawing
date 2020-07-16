import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Figure {
    private List<Point> pointsInsideFigure = new ArrayList<>();
    private List<Point> figureTopPoints = new ArrayList<>();

    public List<Point> getPointsInsideFigure() {
        return pointsInsideFigure;
    }

    public Figure setPointsInsideFigure(List<Point> pointsInsideFigure) {
        this.pointsInsideFigure = pointsInsideFigure;
        return this;
    }

    public List<Point> getFigureTopPoints() {
        return figureTopPoints;
    }

    public Figure setFigureTopPoints(List<Point> figureTopPoints) {
        this.figureTopPoints = figureTopPoints;
        return this;
    }

    public Point findPointBetweenCoordinates(Point firstPoint, Point secondPoint, int coef) {
        int x = (int) (firstPoint.getX() + (coef * secondPoint.getX())) / (1 + coef);
        int y = (int) (firstPoint.getY() + (coef * secondPoint.getY())) / (1 + coef);
        return new Point(x, y);
    }

    public void addPointInsideFigure(Point point) {
        pointsInsideFigure.add(point);
    }

    public void addTopFigurePoint(Point point) {
        figureTopPoints.add(point);
    }

    public void setRandomFigureTops() {
        Random random = new Random();
        int numberOfTops = random.nextInt(10) + 3;
        for (int i = 0; i < numberOfTops; i++) {
            Point point = new Point(random.nextInt(1930), random.nextInt(1030));
            addTopFigurePoint(point);
        }
    }
}
