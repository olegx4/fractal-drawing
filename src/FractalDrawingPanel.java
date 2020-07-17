import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static java.util.Objects.isNull;

public class FractalDrawingPanel extends JPanel {
    private Figure figure = new Figure();
    private final Point firstPoint;
    private Point currentPoint;
    private boolean drawLines = false;

    public FractalDrawingPanel(Point firstPoint) {
        this.firstPoint = firstPoint;
        this.currentPoint = firstPoint;
        this.setFocusable(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    figure.addTopFigurePoint(new Point(e.getX(), e.getY()));
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    if (figure.getPointsInsideFigure().size() != 0) {
                        figure.getPointsInsideFigure().clear();
                    }
                    calculateFractal();
                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    figure = new Figure();
                }
                repaint();
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                figure = new Figure();
                if (e.getKeyChar() == 'r') {
                    drawLines = false;
                } else if (e.getKeyChar() == 'l') {
                    drawLines = true;
                }
                figure.setRandomFigureTops();
                calculateFractal();
                repaint();
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (drawLines) {
            drawFractalLines(g);
        } else {
            drawFractal(g);
        }
        drawFigure(g);
    }

    private void drawFigure(Graphics g) {
        g.drawOval((int) firstPoint.getX(), (int) firstPoint.getY(), 7, 7);

        if (!isNull(figure.getFigureTopPoints())) {
            for (Point p : figure.getFigureTopPoints()) {
                int pointDiameter = 10;
                g.setColor(Color.red);
                g.fillOval((int) p.getX() - 3, (int) p.getY() - 3, pointDiameter, pointDiameter);
            }
        }
    }

    public void calculateFractal() {
        if (figure.getFigureTopPoints().size() != 0) {
            Random random = new Random();
            int iterations;
            if (drawLines) {
                iterations = 500;
            } else {
                iterations = 1_000_000;
            }
            for (int i = 0; i < iterations; i++) {
                int randomInt = random.nextInt(figure.getFigureTopPoints().size());
                currentPoint = figure.findPointBetweenCoordinates(currentPoint, figure.getFigureTopPoints().get(randomInt), 2);
                figure.addPointInsideFigure(currentPoint);
            }
        }
    }

    private void drawFractal(Graphics g) {
        if (!isNull(figure.getPointsInsideFigure())) {
            for (Point p : figure.getPointsInsideFigure()) {
                g.setColor(Color.cyan);
                g.drawOval((int) p.getX(), (int) p.getY(), 1, 1);
            }
        }
    }

    private void drawFractalLines(Graphics g) {
        for (int i = 1; i < figure.getPointsInsideFigure().size(); i++) {
            g.setColor(Color.green);
            Point p = figure.getPointsInsideFigure().get(i - 1);
            Point p2 = figure.getPointsInsideFigure().get(i);
            g.drawLine((int) p.getX(), (int) p.getY(), (int) p2.getX(), (int) p2.getY());
        }
    }
}
