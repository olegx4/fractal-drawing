import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final Point firstPoint = new Point(111, 500);

        List<Point> figureTops = new ArrayList<>();
        figureTops.add(new Point(600, 100));
        figureTops.add(new Point(100, 400));
//        figureTops.add(new Point(1900, 400));
        figureTops.add(new Point(300, 700));
//        figureTops.add(new Point(900, 700));
        //figureTops.add(new Point(400, 0));
//        figureTops.add(new Point(800, 0));
//        figureTops.add(new Point(200, 200));
//        figureTops.add(new Point(600, 200));
//        figureTops.add(new Point(400, 400));
//        figureTops.add(new Point(800, 400));
//        figureTops.add(new Point(200, 600));
//        figureTops.add(new Point(600, 600));


        Figure figure = new Figure().setFigureTopPoints(figureTops);

        final FractalDrawingPanel panel = new FractalDrawingPanel(figure, firstPoint);

        GraphicApp app = new GraphicApp(panel);
        app.show();
    }
}
