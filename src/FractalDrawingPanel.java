import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FractalDrawingPanel extends JPanel {
    private final Figure figure;
    private final Point firstPoint;

    public FractalDrawingPanel(Figure figure, Point firstPoint) {
        this.figure = figure;
        this.firstPoint = firstPoint;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        drawFigure(g, figure.getFigureTopPoints());
    }

    private void drawFigure(Graphics g, List<Point> points) {
        g.drawOval((int) firstPoint.getX(), (int) firstPoint.getY(), 7, 7);

        for (Point p : points) {
            int pointDiameter = 10;
            g.drawOval((int) p.getX(), (int) p.getY(), pointDiameter, pointDiameter);
        }
    }
}
