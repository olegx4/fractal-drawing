import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Figure {
    private List<Point> pointsInsideFigure = new ArrayList<>();
    private List<Point> figureTopPoints;

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
        int x = (int) (firstPoint.getX() + (coef * secondPoint.getX())) / (2 + coef - 1);
        int y = (int) (firstPoint.getY() + (coef * secondPoint.getY())) / (2 + coef - 1);
        return new Point(x, y);
    }

    public void addPointInsideFigure(Point point) {
        pointsInsideFigure.add(point);
    }

    public void addTopFigurePoint(Point point) {
        pointsInsideFigure.add(point);
    }
}
