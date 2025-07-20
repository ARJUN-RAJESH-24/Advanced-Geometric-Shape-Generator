import java.util.Scanner;

// Make sure Shape.java, Circle.java, Square.java, Triangle.java are in the same directory
// or properly structured in your IDE.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Basic Geometry Designer!");
        System.out.println("You can ask me to create shapes like 'circle', 'square', 'triangle'.");
        System.out.println("For a circle, try: 'create circle radius 5 at 0 0'");
        System.out.println("For a square, try: 'create square side 10 at 0 0'");
        System.out.println("For a triangle, try: 'create triangle vertices 0 0 5 0 2.5 4.33'");
        System.out.println("Enter your command (or 'exit' to quit):");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("exit") || command.equals("quit")) {
                System.out.println("Exiting the Basic Geometry Designer. Goodbye!");
                break;
            }

            Shape generatedShape = null;

            if (command.startsWith("create circle")) {
                generatedShape = createCircleFromCommand(command);
            } else if (command.startsWith("create square")) {
                generatedShape = createSquareFromCommand(command); // Call the new method
            } else if (command.startsWith("create triangle")) {
                generatedShape = createTriangleFromCommand(command); // Call the new method
            } else {
                System.out.println("Sorry, I don't understand that command. Please try again.");
            }

            if (generatedShape != null) {
                System.out.println("Generated: " + generatedShape.getRepresentation());
            }
        }
        scanner.close();
    }

    // Existing method for Circle
    private static Circle createCircleFromCommand(String command) {
        try {
            String[] parts = command.split(" ");
            double radius = 0;
            double centerX = 0;
            double centerY = 0;

            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("radius") && i + 1 < parts.length) {
                    radius = Double.parseDouble(parts[i+1]);
                } else if (parts[i].equals("at") && i + 2 < parts.length) {
                    centerX = Double.parseDouble(parts[i+1]);
                    centerY = Double.parseDouble(parts[i+2]);
                }
            }
            return new Circle(centerX, centerY, radius);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numbers for circle. Please check your input format: 'create circle radius <value> at <x> <y>'");
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while creating a circle: " + e.getMessage());
            return null;
        }
    }

    // New method for Square
    private static Square createSquareFromCommand(String command) {
        // Expected format: "create square side 10 at 0 0"
        try {
            String[] parts = command.split(" ");
            double sideLength = 0;
            double topLeftX = 0;
            double topLeftY = 0;

            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("side") && i + 1 < parts.length) {
                    sideLength = Double.parseDouble(parts[i+1]);
                } else if (parts[i].equals("at") && i + 2 < parts.length) {
                    topLeftX = Double.parseDouble(parts[i+1]);
                    topLeftY = Double.parseDouble(parts[i+2]);
                }
            }
            if (sideLength <= 0) {
                 System.err.println("Square side length must be positive.");
                 return null;
            }
            return new Square(topLeftX, topLeftY, sideLength);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numbers for square. Please check your input format: 'create square side <value> at <x> <y>'");
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while creating a square: " + e.getMessage());
            return null;
        }
    }

    // New method for Triangle
    private static Triangle createTriangleFromCommand(String command) {
        // Expected format: "create triangle vertices 0 0 5 0 2.5 4.33"
        try {
            String[] parts = command.split(" ");
            // We need 6 numbers after "vertices"
            // Find the index of "vertices"
            int verticesIndex = -1;
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("vertices")) {
                    verticesIndex = i;
                    break;
                }
            }

            if (verticesIndex != -1 && parts.length >= verticesIndex + 6) {
                double x1 = Double.parseDouble(parts[verticesIndex + 1]);
                double y1 = Double.parseDouble(parts[verticesIndex + 2]);
                double x2 = Double.parseDouble(parts[verticesIndex + 3]);
                double y2 = Double.parseDouble(parts[verticesIndex + 4]);
                double x3 = Double.parseDouble(parts[verticesIndex + 5]);
                double y3 = Double.parseDouble(parts[verticesIndex + 6]);
                return new Triangle(x1, y1, x2, y2, x3, y3);
            } else {
                System.err.println("Invalid format for triangle. Please use: 'create triangle vertices <x1> <y1> <x2> <y2> <x3> <y3>'");
                return null;
            }
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numbers for triangle. Please check your input format: 'create triangle vertices <x1> <y1> <x2> <y2> <x3> <y3>'");
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while creating a triangle: " + e.getMessage());
            return null;
        }
    }
}