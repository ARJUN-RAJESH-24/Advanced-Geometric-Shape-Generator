// VisualizerFrame.java (Update this file)
import javax.swing.*;
import java.awt.*;

public class VisualizerFrame extends JFrame {
    private ShapePanel shapePanel;

    public VisualizerFrame() {
        setTitle("Basic Geometry Designer - Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        shapePanel = new ShapePanel();
        add(shapePanel);

        setVisible(true);
    }

    public void addShape2D(Shape shape) {
        shapePanel.addShape2D(shape);
    }

    public void addShape3D(_3DShape shape) { // New method
        shapePanel.addShape3D(shape);
    }

    public void clearAllShapes() {
        shapePanel.clearShapes();
    }
}