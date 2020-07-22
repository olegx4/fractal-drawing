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

    public void setPointsInsideFigure(List<Point> pointsInsideFigure) {
        this.pointsInsideFigure = pointsInsideFigure;
    }

    public List<Point> getFigureTopPoints() {
        return figureTopPoints;
    }

    public void setFigureTopPoints(List<Point> figureTopPoints) {
        this.figureTopPoints = figureTopPoints;
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
