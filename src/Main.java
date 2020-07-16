import java.awt.*;

public class Main {

    public static void main(String[] args) {
        final Point firstPoint = new Point(600, 500);
        final FractalDrawingPanel panel = new FractalDrawingPanel(firstPoint);

        EventQueue.invokeLater(() -> {
            final GraphicApp app = new GraphicApp(panel);
            app.show();
        });
    }
}
