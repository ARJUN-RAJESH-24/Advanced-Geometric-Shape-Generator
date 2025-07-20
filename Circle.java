// Circle.java
import java.awt.Color;
import java.awt.Graphics2D; // For setting drawing color

public class Circle implements Shape {
    protected double centerX;
    protected double centerY;
    protected double radius;

    public Circle(double centerX, double centerY, double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    // Getters (optional, but good for accessing properties if needed later)
    public double getCenterX() { return centerX; }
    public double getCenterY() { return centerY; }
    public double getRadius() { return radius; }

    @Override
    public String getType() {
        return "Circle";
    }

    @Override
    public String getRepresentation() {
        return String.format("Circle: Center(%.2f,%.2f), Radius=%.2f", centerX, centerY, radius);
    }

    @Override
    public void draw(Graphics2D g2d, int panelWidth, int panelHeight) {
        // Convert mathematical coordinates to screen coordinates
        // Swing's drawOval takes top-left corner (x,y) and width/height
        // Mathematical origin (0,0) is usually center, Y-axis up.
        // Swing origin (0,0) is top-left, Y-axis down.
        int screenDiameter = (int) (radius * 2);
        int screenX = (int) (centerX - radius + panelWidth / 2);
        int screenY = (int) (panelHeight / 2 - (centerY + radius)); // Flip Y-axis

        g2d.setColor(Color.BLUE); // Set drawing color for circle
        g2d.drawOval(screenX, screenY, screenDiameter, screenDiameter);

        // Optional: Draw a small label or marker
        g2d.drawString(String.format("C(%.0f,%.0f)", centerX, centerY), screenX, screenY - 5);
    }
}