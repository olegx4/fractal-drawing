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

    public void addPointInsideFigure(Point point) {
        pointsInsideFigure.add(point);
    }

    public void addTopFigurePoint(Point point) {
        figureTopPoints.add(point);
    }

    public void setRandomFigureTops(int xLimit, int yLimit) {
        Random random = new Random();
        int numberOfTops = random.nextInt(10) + 3;
        for (int i = 0; i < numberOfTops; i++) {
            Point point = new Point(random.nextInt(xLimit), random.nextInt(yLimit));
            addTopFigurePoint(point);
        }
    }
}
