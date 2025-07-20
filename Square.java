// Square.java (Create this file)
public class Square implements Shape {
    private double topLeftX;
    private double topLeftY;
    private double sideLength;

    public Square(double topLeftX, double topLeftY, double sideLength) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.sideLength = sideLength;
    }

    @Override
    public String getType() {
        return "Square";
    }

    @Override
    public String getRepresentation() {
        // We can also list all four vertices for a more complete representation
        double p1x = topLeftX;
        double p1y = topLeftY;
        double p2x = topLeftX + sideLength;
        double p2y = topLeftY;
        double p3x = topLeftX + sideLength;
        double p3y = topLeftY + sideLength;
        double p4x = topLeftX;
        double p4y = topLeftY + sideLength;

        return String.format(
            "Square: Top-Left(%.2f,%.2f), Side=%.2f. Vertices: (%.2f,%.2f), (%.2f,%.2f), (%.2f,%.2f), (%.2f,%.2f)",
            topLeftX, topLeftY, sideLength,
            p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y
        );
    }
}