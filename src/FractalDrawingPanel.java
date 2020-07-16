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
        calculateFractal();
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawFractalLines(g);
        //drawFigure(g);
    }

    private void drawFigure(Graphics g) {
        g.drawOval((int) firstPoint.getX(), (int) firstPoint.getY(), 7, 7);

        for (Point p : figure.getFigureTopPoints()) {
            int pointDiameter = 10;
            g.setColor(Color.yellow);
            g.fillOval((int) p.getX() - 3, (int) p.getY() - 3, pointDiameter, pointDiameter);
        }
    }

    private void calculateFractal() {
        Random random = new Random();
        for (int i = 0; i < 1_000; i++) {
            int randomInt = random.nextInt(figure.getFigureTopPoints().size());
            currentPoint = figure.findPointBetweenCoordinates(currentPoint, figure.getFigureTopPoints().get(randomInt), 2);
            figure.addPointInsideFigure(currentPoint);
        }
    }

    private void drawFractal(Graphics g) {
        for (Point p : figure.getPointsInsideFigure()) {
            g.setColor(Color.green);
            g.drawLine((int) p.getX(), (int) p.getY(), 1, 1);
        }
    }

    private void drawFractalLines(Graphics g) {
        for (int i = 1; i < figure.getPointsInsideFigure().size(); i++) {
            g.setColor(Color.green);
            Point p = figure.getPointsInsideFigure().get(i-1);
            Point p2 = figure.getPointsInsideFigure().get(i);
            g.drawLine((int) p.getX(), (int) p.getY(), (int) p2.getX(), (int) p2.getY());
        }
    }
}
