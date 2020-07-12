import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final Point firstPoint = new Point(300, 300);

        List<Point> fractalTops = new ArrayList<>();
        fractalTops.add(new Point(400, 450));
        fractalTops.add(new Point(100, 100));
        fractalTops.add(new Point(900, 200));

        Figure figure = new Figure().setFigureTopPoints(fractalTops);

        final FractalDrawingPanel panel = new FractalDrawingPanel(figure, firstPoint);


        GraphicApp app = new GraphicApp(panel);
        app.show();
    }
}
