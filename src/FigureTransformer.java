import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FigureTransformer extends Figure {

    public FigureTransformer() {
    }

    public java.util.List<Point> moveXCoordinatesOn(java.util.List<Point> points, int shift) {
        List<Point> result = new ArrayList<>();
        if (points.size() > 0) {
            for (Point currentPoint : points) {
                final int newXCoordinate = (int) (currentPoint.getX() + shift);
                final int newYCoordinate = (int) currentPoint.getY();
                result.add(new Point(newXCoordinate, newYCoordinate));
            }
        } else {
            System.out.println("Figure does`nt have points");
        }
        return result;
    }

    public List<Point> moveYCoordinatesOn(List<Point> points, int shift) {
        List<Point> result = new ArrayList<>();
        if (points.size() > 0) {
            for (Point currentPoint : points) {
                final int newXCoordinate = (int) currentPoint.getX();
                final int newYCoordinate = (int) (currentPoint.getY() + shift);
                result.add(new Point(newXCoordinate, newYCoordinate));
            }
        } else {
            System.out.println("Figure does`nt have points");
        }
        return result;
    }

    public void moveXCoordinatesOn(int shift) {
        this.setPointsInsideFigure(
                moveXCoordinatesOn(this.getPointsInsideFigure(), shift));
        this.setFigureTopPoints(
                moveXCoordinatesOn(this.getFigureTopPoints(), shift));
    }

    public void moveYCoordinatesOn(int shift) {
        this.setPointsInsideFigure(
                moveYCoordinatesOn(this.getPointsInsideFigure(), shift));
        this.setFigureTopPoints(
                moveYCoordinatesOn(this.getFigureTopPoints(), shift));
    }

    public List<Point> rotatePointsOn(List<Point> points, int degree, Point centralPoint) {
        PointCalculator pointCalculator = new PointCalculator();
        return points
                .stream()
                .map(point -> pointCalculator.rotatePointCoordinatesOn(point, degree, centralPoint))
                .collect(Collectors.toList());
    }

    public void rotateFigureOn(int degree, Point centralPoint) {
        this.setPointsInsideFigure(
                rotatePointsOn(this.getPointsInsideFigure(), degree, centralPoint));
        this.setFigureTopPoints(
                rotatePointsOn(this.getFigureTopPoints(), degree, centralPoint));
    }

    public List<Point> zoomCoordinates(List<Point> points, double coefficient) {
        List<Point> result = new ArrayList<>();
        if (points.size() > 0) {
            for (Point point : points) {
                final int newXCoordinate = (int) (point.getX() * coefficient);
                final int newYCoordinate = (int) (point.getY() * coefficient);
                result.add(new Point(newXCoordinate, newYCoordinate));
            }
        } else {
            System.out.println("Figure does`nt have points");
        }
        return result;
    }

    public List<Point> zoomCoordinates(List<Point> points, double coefficient, Point centralPoint) {
        List<Point> result = new ArrayList<>();
        if (points.size() > 0) {
            for (Point point : points) {
                final int resultX = (int) ((coefficient > 1)
                        ? ((point.getX()) * coefficient) - centralPoint.getX()
                        : ((point.getX() - centralPoint.getX()) * coefficient) + centralPoint.getX());
                final int resultY = (int) ((coefficient > 1)
                        ? ((point.getY()) * coefficient) - centralPoint.getY()
                        : ((point.getY() - centralPoint.getY()) * coefficient) + centralPoint.getY());
                final Point resultPoint = new Point(resultX, resultY);
                result.add(resultPoint);
            }
        } else {
            System.out.println("Figure does`nt have points");
        }
        return result;
    }

    public void zoomFigure(double coefficient) {
        this.setPointsInsideFigure(
                zoomCoordinates(this.getPointsInsideFigure(), coefficient));
        this.setFigureTopPoints(
                zoomCoordinates(this.getFigureTopPoints(), coefficient));
    }

    public void zoomFigure(double coefficient, Point centralPoint) {
        this.setPointsInsideFigure(
                zoomCoordinates(this.getPointsInsideFigure(), coefficient, centralPoint));
        this.setFigureTopPoints(
                zoomCoordinates(this.getFigureTopPoints(), coefficient, centralPoint));
    }
}
