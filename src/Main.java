import java.awt.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final Point firstPoint = new Point(600, 500);
        final DefinedFigure definedFigure = new DefinedFigure();

        final List<Point> figureTops = definedFigure.triangle;
        Figure figure = new Figure().setFigureTopPoints(figureTops);

        final FractalDrawingPanel panel = new FractalDrawingPanel(figure, firstPoint);

        GraphicApp app = new GraphicApp(panel);
        app.show();
    }
}
