// Square.java
import java.awt.Color;
import java.awt.Graphics2D;

public class Square implements Shape {
    protected double topLeftX;
    protected double topLeftY;
    protected double sideLength;

    public Square(double topLeftX, double topLeftY, double sideLength) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.sideLength = sideLength;
    }

    // Getters
    public double getTopLeftX() { return topLeftX; }
    public double getTopLeftY() { return topLeftY; }
    public double getSideLength() { return sideLength; }

    @Override
    public String getType() {
        return "Square";
    }

    @Override
    public String getRepresentation() {
        // Also include all four vertices for a complete representation
        double p1x = topLeftX;
        double p1y = topLeftY;
        double p2x = topLeftX + sideLength;
        double p2y = topLeftY;
        double p3x = topLeftX + sideLength;
        double p3y = topLeftY - sideLength; // Y-coordinate decreases for bottom-right corner if starting from top-left
        double p4x = topLeftX;
        double p4y = topLeftY - sideLength; // Y-coordinate decreases for bottom-left corner

        return String.format(
            "Square: Top-Left(%.2f,%.2f), Side=%.2f. Vertices: (%.2f,%.2f), (%.2f,%.2f), (%.2f,%.2f), (%.2f,%.2f)",
            topLeftX, topLeftY, sideLength,
            p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y
        );
    }

    @Override
    public void draw(Graphics2D g2d, int panelWidth, int panelHeight) {
        // Convert mathematical coordinates (top-left) to screen coordinates
        // For drawing a rectangle from its top-left corner
        int screenX = (int) (topLeftX + panelWidth / 2);
        // We assume topLeftY is the mathematical Y coordinate of the top edge.
        // In Swing, this should be (height/2 - topLeftY) to convert.
        // Then, the height of the rectangle in Swing is 'sideLength' going downwards.
        int screenY = (int) (panelHeight / 2 - topLeftY);
        int screenSide = (int) sideLength;

        g2d.setColor(Color.RED); // Set drawing color for square
        g2d.drawRect(screenX, screenY, screenSide, screenSide);

        // Optional: Draw a label
        g2d.drawString(String.format("S(%.0f,%.0f)", topLeftX, topLeftY), screenX, screenY - 5);
    }
}