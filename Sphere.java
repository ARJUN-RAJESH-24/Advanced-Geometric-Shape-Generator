// Sphere.java (Create this new file)
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Point2D;

public class Sphere extends _3DShape {
    private double radius;

    public Sphere(double centerX, double centerY, double centerZ, double radius) {
        super(centerX, centerY, centerZ, Color.CYAN.darker()); // Sphere will be dark cyan
        this.radius = radius;
    }

    @Override
    public String getType() {
        return "Sphere";
    }

    @Override
    public String getRepresentation() {
        return String.format("Sphere: Center(%.2f,%.2f,%.2f), Radius=%.2f",
                             centerX, centerY, centerZ, radius);
    }

    @Override
    public void draw(Graphics2D g2d, int panelWidth, int panelHeight) {
        g2d.setColor(color);

        // Project center
        Point2D centerProjected = project(centerX, centerY, centerZ, panelWidth, panelHeight);
        int scrCx = (int) centerProjected.getX();
        int scrCy = (int) centerProjected.getY();
        int scrDiameter = (int) (radius * 2);

        // Draw main circle (XY plane projection)
        g2d.drawOval(scrCx - scrDiameter / 2, scrCy - scrDiameter / 2, scrDiameter, scrDiameter);

        // Draw an ellipse for the XZ plane projection (simulates depth/rotation)
        // This makes it look more spherical than just a circle.
        // The width is diameter, height is scaled down to show it's "flat" in the Z direction
        g2d.drawOval(scrCx - scrDiameter / 2, (int)(scrCy - radius * 0.5), scrDiameter, (int)(radius * 1.0)); // Flattened ellipse

        // Draw an ellipse for the YZ plane projection (rotated)
        g2d.drawOval((int)(scrCx - radius * 0.5), scrCy - scrDiameter / 2, (int)(radius * 1.0), scrDiameter); // Narrowed ellipse

        g2d.drawString(String.format("Sphere(%.0f,%.0f,%.0f)", centerX, centerY, centerZ), scrCx, scrCy - scrDiameter / 2 - 5);
    }
}