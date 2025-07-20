// Cube.java (Create this new file)
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Point2D;

public class Cube extends _3DShape {
    private double sideLength;

    public Cube(double centerX, double centerY, double centerZ, double sideLength) {
        super(centerX, centerY, centerZ, Color.MAGENTA); // Cube will be magenta
        this.sideLength = sideLength;
    }

    @Override
    public String getType() {
        return "Cube";
    }

    @Override
    public String getRepresentation() {
        return String.format("Cube: Center(%.2f,%.2f,%.2f), Side=%.2f",
                             centerX, centerY, centerZ, sideLength);
    }

    @Override
    public void draw(Graphics2D g2d, int panelWidth, int panelHeight) {
        g2d.setColor(color);

        double h = sideLength / 2; // Half side length

        // Define 8 vertices of the cube (relative to its center)
        // Front Face (positive Z, towards viewer)
        Point2D p1 = project(centerX - h, centerY + h, centerZ + h, panelWidth, panelHeight); // Top-Left-Front
        Point2D p2 = project(centerX + h, centerY + h, centerZ + h, panelWidth, panelHeight); // Top-Right-Front
        Point2D p3 = project(centerX + h, centerY - h, centerZ + h, panelWidth, panelHeight); // Bottom-Right-Front
        Point2D p4 = project(centerX - h, centerY - h, centerZ + h, panelWidth, panelHeight); // Bottom-Left-Front

        // Back Face (negative Z, away from viewer)
        Point2D p5 = project(centerX - h, centerY + h, centerZ - h, panelWidth, panelHeight); // Top-Left-Back
        Point2D p6 = project(centerX + h, centerY + h, centerZ - h, panelWidth, panelHeight); // Top-Right-Back
        Point2D p7 = project(centerX + h, centerY - h, centerZ - h, panelWidth, panelHeight); // Bottom-Right-Back
        Point2D p8 = project(centerX - h, centerY - h, centerZ - h, panelWidth, panelHeight); // Bottom-Left-Back

        // Draw edges
        // Front Face
        g2d.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
        g2d.drawLine((int)p2.getX(), (int)p2.getY(), (int)p3.getX(), (int)p3.getY());
        g2d.drawLine((int)p3.getX(), (int)p3.getY(), (int)p4.getX(), (int)p4.getY());
        g2d.drawLine((int)p4.getX(), (int)p4.getY(), (int)p1.getX(), (int)p1.getY());

        // Back Face
        g2d.drawLine((int)p5.getX(), (int)p5.getY(), (int)p6.getX(), (int)p6.getY());
        g2d.drawLine((int)p6.getX(), (int)p6.getY(), (int)p7.getX(), (int)p7.getY());
        g2d.drawLine((int)p7.getX(), (int)p7.getY(), (int)p8.getX(), (int)p8.getY());
        g2d.drawLine((int)p8.getX(), (int)p8.getY(), (int)p5.getX(), (int)p5.getY());

        // Connecting Edges
        g2d.drawLine((int)p1.getX(), (int)p1.getY(), (int)p5.getX(), (int)p5.getY());
        g2d.drawLine((int)p2.getX(), (int)p2.getY(), (int)p6.getX(), (int)p6.getY());
        g2d.drawLine((int)p3.getX(), (int)p3.getY(), (int)p7.getX(), (int)p7.getY());
        g2d.drawLine((int)p4.getX(), (int)p4.getY(), (int)p8.getX(), (int)p8.getY());

        g2d.drawString(String.format("Cube(%.0f,%.0f,%.0f)", centerX, centerY, centerZ), (int)p1.getX(), (int)p1.getY() - 5);
    }
}