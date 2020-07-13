import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final Point firstPoint = new Point(500, 500);

        List<Point> figureTops = new ArrayList<>();
        figureTops.add(new Point(100, 400));
        figureTops.add(new Point(600, 100));
        figureTops.add(new Point(1100, 400));
        //figureTops.add(new Point(300, 700));
        figureTops.add(new Point(900, 700));

        Figure figure = new Figure().setFigureTopPoints(figureTops);

        final FractalDrawingPanel panel = new FractalDrawingPanel(figure, firstPoint);

        GraphicApp app = new GraphicApp(panel);
        app.show();
    }
}
