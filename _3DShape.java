// _3DShape.java (Create this new file)
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D; // To represent projected 2D points

public abstract class _3DShape {
    protected double centerX, centerY, centerZ;
    protected Color color;

    public _3DShape(double centerX, double centerY, double centerZ, Color color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;
        this.color = color;
    }

    public abstract String getType();
    public abstract String getRepresentation();
    public abstract void draw(Graphics2D g2d, int panelWidth, int panelHeight);

    // --- Basic 3D to 2D Projection (Isometric-like) ---
    // This is a simple orthographic projection with a slight "isometric" feel
    // Z-axis values affect X and Y on the 2D plane
    protected Point2D project(double x, double y, double z, int panelWidth, int panelHeight) {
        // Adjust for perspective/depth (simple effect: farther objects are smaller/shifted)
        // For a more traditional isometric projection, you'd use specific angles.
        // Let's use a very simple Z-based shift:
        double projectedX = x + z * 0.5; // Simple shift along X based on Z
        double projectedY = y - z * 0.5; // Simple shift along Y based on Z (simulates "up" for positive Z)

        // Convert to screen coordinates (relative to panel center, flip Y)
        int screenX = (int) (projectedX + panelWidth / 2);
        int screenY = (int) (panelHeight / 2 - projectedY); // Flip Y-axis

        return new Point2D.Double(screenX, screenY);
    }
}