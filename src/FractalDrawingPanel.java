import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FractalDrawingPanel extends JPanel {
    private final Figure figure;
    private final Point firstPoint;
    private Point currentPoint;

    public FractalDrawingPanel(Figure figure, Point firstPoint) {
        this.figure = figure;
        this.firstPoint = firstPoint;
        this.currentPoint = firstPoint;
    }

    public void paint(Graphics g) {
        super.paint(g);
        calculateFractal(g);
        drawFractal(g);
        drawFigure(g);
    }

    private void drawFigure(Graphics g) {
        g.drawOval((int) firstPoint.getX(), (int) firstPoint.getY(), 7, 7);

        for (Point p : figure.getFigureTopPoints()) {
            int pointDiameter = 10;
            g.drawOval((int) p.getX(), (int) p.getY(), pointDiameter, pointDiameter);
        }
    }

    private void calculateFractal(Graphics g) {
        Random random = new Random();
        int randomInt;
        for (int i = 0; i < 1_000_000; i++) {
            randomInt = random.nextInt(figure.getFigureTopPoints().size());
            //int topIndex = rule.findIndexOfTop(randomInt);
            currentPoint = figure.findCentralPoint(currentPoint, figure.getFigureTopPoints().get(randomInt));
            figure.addPointInsideFigure(currentPoint);
        }
    }

    private void drawFractal(Graphics g) {
        for (Point p : figure.getPointsInsideFigure()) {
            g.drawOval((int) p.getX(), (int) p.getY(), 1, 1);
        }
    }
}
