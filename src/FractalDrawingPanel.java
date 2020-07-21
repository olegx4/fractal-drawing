import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static java.util.Objects.isNull;

public class FractalDrawingPanel extends JPanel {
    private final int LINE_ITERATIONS = 100;
    private final int CHAOS_GAME_ITERATIONS = 1_000_000;
    private final Point firstPoint;
    private final PointCalculator pointCalculator = new PointCalculator();
    private String fractalType = FractalType.ChaosGame.toString();
    private int currentIterations = CHAOS_GAME_ITERATIONS;
    private Figure figure = new Figure();
    private boolean showTopPoints = true;

    public FractalDrawingPanel(Point firstPoint) {
        this.firstPoint = firstPoint;
        this.setFocusable(true);
        this.addMouseListener(new MouseCatcher());
        this.addKeyListener(new KeyCatcher());
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (fractalType.equals(FractalType.Linear.name())) {
            drawLines(g, figure.getPointsInsideFigure());
        } else if (fractalType.equals(FractalType.ChaosGame.name())) {
            drawFractal(g);
        } else {
            drawLines(g, figure.getPointsInsideFigure());
        }
        if (showTopPoints) {
            drawFigure(g);
        }
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

    private void drawLines(Graphics g, List<Point> points) {
        for (int i = 1; i < points.size(); i++) {
            g.setColor(Color.green);
            Point p = points.get(i - 1);
            Point p2 = points.get(i);
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
            if (e.getKeyChar() == 'r') {
                fractalType = FractalType.ChaosGame.toString();
                currentIterations = CHAOS_GAME_ITERATIONS;
                makeRandomActions();
            } else if (e.getKeyChar() == 'l') {
                fractalType = FractalType.Linear.toString();
                currentIterations = LINE_ITERATIONS;
                makeRandomActions();
            } else if (e.getKeyChar() == 'd') {
                fractalType = FractalType.Dragon.toString();
                if (currentIterations > 20) {
                    currentIterations = 1;
                    figure = new Figure();
                }
                figure.setPointsInsideFigure(
                        pointCalculator.calculateDragonCurvePoints(
                                figure.getFigureTopPoints(),
                                currentIterations,
                                90));
                figure.setFigureTopPoints(figure.getPointsInsideFigure());
                repaint();
            } else if (e.getKeyChar() == 'i') {
                currentIterations = ++currentIterations;
                figure.getPointsInsideFigure().clear();
            } else if (e.getKeyChar() == 'q') {
                currentIterations = 1;
            } else if (e.getKeyChar() == 'h') {
                showTopPoints = !showTopPoints;
                repaint();
            }
        }

        private void makeRandomActions() {
            figure = new Figure();
            figure.setRandomFigureTops(getWidth(), getHeight());
            figure.setPointsInsideFigure(
                    pointCalculator.calculateChaosGameFractalPoints(
                            figure.getFigureTopPoints(),
                            currentIterations,
                            firstPoint));
            repaint();
        }
    }
}
