// ShapePanel.java (Update this file)
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapePanel extends JPanel {
    private List<Shape> shapes2D = new ArrayList<>();
    private List<_3DShape> shapes3D = new ArrayList<>(); // New list for 3D shapes

    public ShapePanel() {
        // Constructor, nothing special needed here initially
    }

    public void addShape2D(Shape shape) {
        this.shapes2D.add(shape);
        repaint();
    }

    public void addShape3D(_3DShape shape) { // New method for 3D shapes
        this.shapes3D.add(shape);
        repaint();
    }

    public void clearShapes() {
        this.shapes2D.clear();
        this.shapes3D.clear(); // Clear both lists
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawAxes(g2d, getWidth(), getHeight());

        // Draw all 2D shapes
        for (Shape shape : shapes2D) {
            shape.draw(g2d, getWidth(), getHeight());
        }

        // Draw all 3D shapes
        for (_3DShape shape : shapes3D) { // Iterate and draw 3D shapes
            shape.draw(g2d, getWidth(), getHeight());
        }
    }

    private void drawAxes(Graphics2D g2d, int width, int height) {
        g2d.setColor(Color.GRAY);

        // Draw X-axis
        g2d.drawLine(0, height / 2, width, height / 2);
        // Draw Y-axis
        g2d.drawLine(width / 2, 0, width / 2, height);
        // Draw Z-axis (simple projection)
        g2d.drawLine(width / 2, height / 2, (int)(width / 2 + 100 * 0.5), (int)(height / 2 - 100 * 0.5)); // Line along projected Z

        // Draw axis labels
        g2d.drawString("X", width - 15, height / 2 + 15);
        g2d.drawString("Y", width / 2 - 15, 15);
        g2d.drawString("Z", (int)(width / 2 + 100 * 0.5) + 5, (int)(height / 2 - 100 * 0.5) - 5);
        g2d.drawString("(0,0,0)", width / 2 + 5, height / 2 - 5);
    }
}