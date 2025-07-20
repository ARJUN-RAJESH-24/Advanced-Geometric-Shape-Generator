// Polygon.java (Create this new file)
import java.awt.Color;
import java.awt.Graphics2D;

public class Polygon implements Shape {
    private int numSides;
    private double centerX, centerY;
    private double radius; // Distance from center to a vertex (circumradius)

    public Polygon(int numSides, double centerX, double centerY, double radius) {
        if (numSides < 3) {
            throw new IllegalArgumentException("A polygon must have at least 3 sides.");
        }
        this.numSides = numSides;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public int getNumSides() { return numSides; }
    public double getCenterX() { return centerX; }
    public double getCenterY() { return centerY; }
    public double getRadius() { return radius; }

    @Override
    public String getType() {
        return numSides + "-sided Polygon";
    }

    @Override
    public String getRepresentation() {
        return String.format("%d-sided Polygon: Center(%.2f,%.2f), Radius=%.2f",
                             numSides, centerX, centerY, radius);
    }

    @Override
    public void draw(Graphics2D g2d, int panelWidth, int panelHeight) {
        g2d.setColor(Color.ORANGE.darker()); // Set color for polygons

        int[] xPoints = new int[numSides];
        int[] yPoints = new int[numSides];

        for (int i = 0; i < numSides; i++) {
            double angle = 2 * Math.PI * i / numSides; // Angle in radians
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            // Convert to screen coordinates
            xPoints[i] = (int) (x + panelWidth / 2);
            yPoints[i] = (int) (panelHeight / 2 - y); // Flip Y-axis
        }

        g2d.drawPolygon(xPoints, yPoints, numSides);
        g2d.drawString(String.format("%d-gon(%.0f,%.0f)", numSides, centerX, centerY), xPoints[0], yPoints[0] - 5);
    }
}