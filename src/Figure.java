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

    public Point findCentralPoint(Point firstPoint, Point secondPoint) {
        int x = (int) (firstPoint.getX() + secondPoint.getX()) / 2;
        int y = (int) (firstPoint.getY() + secondPoint.getY()) / 2;
        return new Point(x, y);
    }

    public void addPointInsideFigure(Point point) {
        pointsInsideFigure.add(point);
    }

    public void addTopFigurePoint(Point point) {
        pointsInsideFigure.add(point);
    }
}
