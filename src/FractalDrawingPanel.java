import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.util.Objects.isNull;

public class FractalDrawingPanel extends JPanel {
    private final int LINE_ITERATIONS = 100;
    private final int CHAOS_GAME_ITERATIONS = 1_000_000;
    private final Point firstPoint;
    private final PointCalculator pointCalculator = new PointCalculator();
    private boolean drawLines = false;
    private int currentIterations = CHAOS_GAME_ITERATIONS;
    private Figure figure = new Figure();


    public FractalDrawingPanel(Point firstPoint) {
        this.firstPoint = firstPoint;
        this.setFocusable(true);
        this.addMouseListener(new MouseCatcher());
        this.addKeyListener(new KeyCatcher());
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

    private class MouseCatcher extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                figure.addTopFigurePoint(new Point(e.getX(), e.getY()));
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                figure.getPointsInsideFigure().clear();
                figure.setPointsInsideFigure(
                        pointCalculator.calculateChaosGameFractalPoints(
                                figure.getFigureTopPoints(),
                                currentIterations,
                                firstPoint));
            } else if (e.getButton() == MouseEvent.BUTTON2) {
                figure = new Figure();
            }
            repaint();
        }
    }

    private class KeyCatcher extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            figure = new Figure();
            if (e.getKeyChar() == 'r') {
                drawLines = false;
                currentIterations = CHAOS_GAME_ITERATIONS;
            } else if (e.getKeyChar() == 'l') {
                drawLines = true;
                currentIterations = LINE_ITERATIONS;
            }
            figure.setRandomFigureTops();
            figure.setPointsInsideFigure(
                    pointCalculator.calculateChaosGameFractalPoints(
                            figure.getFigureTopPoints(),
                            currentIterations,
                            firstPoint));
            repaint();
        }
    }
}
