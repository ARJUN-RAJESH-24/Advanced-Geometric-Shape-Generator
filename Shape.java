// Shape.java
import java.awt.Graphics2D;

public interface Shape {
    String getType();
    String getRepresentation();
    void draw(Graphics2D g2d, int panelWidth, int panelHeight);
}