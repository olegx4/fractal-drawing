import javax.swing.*;
import java.awt.*;

public class GraphicApp {
    private JFrame frame;
    private final FractalDrawingPanel panel;

    public GraphicApp(FractalDrawingPanel panel) {
        this.panel = panel;
        createFrame();
        initComponents();
    }

    private void initComponents() {
        Container mainContainer = frame.getContentPane();
        panel.setBackground(Color.BLACK);
        mainContainer.add(panel);
    }

    private void createFrame() {
        frame = new JFrame("Fractal-drawing-app");
        frame.setSize(1980, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void show() {
        frame.setVisible(true);
    }
}
