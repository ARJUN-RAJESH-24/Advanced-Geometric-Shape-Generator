// Triangle.java
import java.awt.Color;
import java.awt.Graphics2D;

public class Triangle implements Shape {
    protected double x1, y1;
    protected double x2, y2;
    protected double x3, y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    // Getters
    public double getX1() { return x1; }
    public double getY1() { return y1; }
    public double getX2() { return x2; }
    public double getY2() { return y2; }
    public double getX3() { return x3; }
    public double getY3() { return y3; }

    @Override
    public String getType() {
        return "Triangle";
    }

    @Override
    public String getRepresentation() {
        return String.format(
            "Triangle: Vertices (%.2f,%.2f), (%.2f,%.2f), (%.2f,%.2f)",
            x1, y1, x2, y2, x3, y3
        );
    }

    @Override
    public void draw(Graphics2D g2d, int panelWidth, int panelHeight) {
        // Convert mathematical coordinates of all three vertices to screen coordinates
        int sx1 = (int) (x1 + panelWidth / 2);
        int sy1 = (int) (panelHeight / 2 - y1); // Flip Y-axis
        int sx2 = (int) (x2 + panelWidth / 2);
        int sy2 = (int) (panelHeight / 2 - y2);
        int sx3 = (int) (x3 + panelWidth / 2);
        int sy3 = (int) (panelHeight / 2 - y3);

        // Create int arrays for x and y coordinates, as required by drawPolygon
        int[] xPoints = {sx1, sx2, sx3};
        int[] yPoints = {sy1, sy2, sy3};

        g2d.setColor(Color.GREEN.darker()); // Set drawing color for triangle
        g2d.drawPolygon(xPoints, yPoints, 3); // Draw the triangle

        // Optional: Draw a label for the first vertex
        g2d.drawString(String.format("T(%.0f,%.0f)", x1, y1), sx1, sy1 - 5);
    }
}