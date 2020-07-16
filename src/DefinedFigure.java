import java.awt.*;
import java.util.List;

public class DefinedFigure {

    final List<Point> triangle = List.of(
            new Point(600, 100),
            new Point(300, 700),
            new Point(600, 500),
            new Point(900, 700));

    final List<Point> square = List.of(
            new Point(100, 100),
            new Point(600, 100),
            new Point(100, 600),
            new Point(350, 350),
            new Point(600, 600));

    final List<Point> pentagon = List.of(
            new Point(100, 400),
            new Point(600, 100),
            new Point(1100, 400),
            new Point(300, 800),
            new Point(600, 500),
            new Point(900, 800));

    final List<Point> hexagon = List.of(
            new Point(0, 200),
            new Point(600, 0),
            new Point(1200, 200),
            new Point(0, 700),
            new Point(1200, 700),
            new Point(600, 900));
}
