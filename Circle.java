class Circle implements Shape {
    private double centerX;
    private double centerY;
    private double radius;

    public Circle(double centerX, double centerY, double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public String getType() {
        return "Circle";
    }

    @Override
    public String getRepresentation() {
        return String.format("Circle: Center(%.2f,%.2f), Radius=%.2f", centerX, centerY, radius);
    }
}