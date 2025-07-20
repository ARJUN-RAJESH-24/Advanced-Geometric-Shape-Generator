// Triangle.java (Create this file)
public class Triangle implements Shape {
    private double x1, y1;
    private double x2, y2;
    private double x3, y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

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
}