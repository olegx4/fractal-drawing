import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static java.util.Objects.isNull;

public class FractalDrawingPanel extends JPanel {
    private final int LINE_ITERATIONS = 100;
    private final int CHAOS_GAME_ITERATIONS = 1_000_000;
    private final Point firstPoint;
    private final PointCalculator pointCalculator = new PointCalculator();
    private String fractalType = FractalType.ChaosGame.toString();
    private int currentIterations = CHAOS_GAME_ITERATIONS;
    private FigureTransformer figure = new FigureTransformer();
    private boolean showTopPoints = true;
    final Action action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            figure.rotateFigureOn(-90, new Point(getWidth() / 2, getHeight() / 2));
        }
    };
    final KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.SHIFT_DOWN_MASK);

    public FractalDrawingPanel(Point firstPoint) {
        this.firstPoint = firstPoint;
        this.setFocusable(true);
        this.addMouseListener(new MouseCatcher());
        this.addKeyListener(new KeyCatcher());
        this.getActionMap().put("Rotate figure left", action);
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "Rotate figure left");
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
                figure = new FigureTransformer();
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
                }
                try {
                    figure.setPointsInsideFigure(
                            pointCalculator.calculateDragonCurvePoints(
                                    figure.getFigureTopPoints(),
                                    currentIterations,
                                    90));
                    figure.setFigureTopPoints(figure.getPointsInsideFigure());
                } catch (OutOfMemoryError ex) {
                    System.out.println("Out of memory");
                    figure = new FigureTransformer();
                }
                repaint();
            } else if (e.getKeyChar() == 'i') {
                currentIterations = ++currentIterations;
                figure.getPointsInsideFigure().clear();
            } else if (e.getKeyChar() == 'q') {
                currentIterations = 1;
            } else if (e.getKeyChar() == 'h') {
                showTopPoints = !showTopPoints;
                repaint();
            } else if (e.getKeyChar() == 'x') {
                figure.zoomFigure(2, new Point(getWidth() / 2, getHeight() / 2));
                repaint();
            } else if (e.getKeyChar() == 'X') {
                figure.zoomFigure(0.5, new Point(getWidth() / 2, getHeight() / 2));
                repaint();
            } else if (e.getKeyChar() == 'Z') {
                figure.zoomFigure(2);
                repaint();
            } else if (e.getKeyChar() == 'z') {
                figure.zoomFigure(0.5);
                repaint();
            } else if (e.getKeyCode() >= 37 && e.getKeyCode() <= 40) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && e.isShiftDown()) {
                    figure.rotateFigureOn(90, new Point(getWidth() / 2, getHeight() / 2));
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    figure.moveXCoordinatesOn(50);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    figure.moveXCoordinatesOn(-50);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    figure.moveYCoordinatesOn(-50);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    figure.moveYCoordinatesOn(50);
                }
                repaint();
            }
        }

        private void makeRandomActions() {
            figure = new FigureTransformer();
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
