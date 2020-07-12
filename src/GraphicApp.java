import javax.swing.*;

public class GraphicApp {
    private JFrame frame;

    public GraphicApp(){
        createFrame();
    }

    private void createFrame() {
        frame = new JFrame("Fractal-drawing-app");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void show(){
        frame.setVisible(true);
    }
}
