// Cylinder.java (Create this new file)
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Point2D;

public class Cylinder extends _3DShape {
    private double radius;
    private double height;

    public Cylinder(double centerX, double centerY, double centerZ, double radius, double height) {
        super(centerX, centerY, centerZ, Color.decode("#8B4513")); // Saddle Brown for cylinder
        this.radius = radius;
        this.height = height;
    }

    @Override
    public String getType() {
        return "Cylinder";
    }

    @Override
    public String getRepresentation() {
        return String.format("Cylinder: Center(%.2f,%.2f,%.2f), Radius=%.2f, Height=%.2f",
                             centerX, centerY, centerZ, radius, height);
    }

    @Override
    public void draw(Graphics2D g2d, int panelWidth, int panelHeight) {
        g2d.setColor(color);

        // Top and Bottom centers of the cylinder
        double topZ = centerZ + height / 2;
        double bottomZ = centerZ - height / 2;

        Point2D topCenter = project(centerX, centerY, topZ, panelWidth, panelHeight);
        Point2D bottomCenter = project(centerX, centerY, bottomZ, panelWidth, panelHeight);

        int scrDiameter = (int) (radius * 2);
        int ellipseHeight = (int) (radius * 0.6); // Make ellipses look slightly flattened

        // Draw top ellipse
        g2d.drawOval((int)(topCenter.getX() - radius), (int)(topCenter.getY() - ellipseHeight / 2), scrDiameter, ellipseHeight);

        // Draw bottom ellipse
        g2d.drawOval((int)(bottomCenter.getX() - radius), (int)(bottomCenter.getY() - ellipseHeight / 2), scrDiameter, ellipseHeight);

        // Draw connecting vertical lines (sides)
        // Top-left to Bottom-left
        Point2D pTopLeft = project(centerX - radius, centerY, topZ, panelWidth, panelHeight);
        Point2D pBottomLeft = project(centerX - radius, centerY, bottomZ, panelWidth, panelHeight);
        g2d.drawLine((int)pTopLeft.getX(), (int)pTopLeft.getY(), (int)pBottomLeft.getX(), (int)pBottomLeft.getY());

        // Top-right to Bottom-right
        Point2D pTopRight = project(centerX + radius, centerY, topZ, panelWidth, panelHeight);
        Point2D pBottomRight = project(centerX + radius, centerY, bottomZ, panelWidth, panelHeight);
        g2d.drawLine((int)pTopRight.getX(), (int)pTopRight.getY(), (int)pBottomRight.getX(), (int)pBottomRight.getY());

        g2d.drawString(String.format("Cyl(%.0f,%.0f,%.0f)", centerX, centerY, centerZ), (int)topCenter.getX(), (int)topCenter.getY() - ellipseHeight / 2 - 5);
    }
}