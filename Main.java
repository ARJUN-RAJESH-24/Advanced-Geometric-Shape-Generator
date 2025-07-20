// Main.java (Update this file)
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Main {
    private static VisualizerFrame visualizer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            visualizer = new VisualizerFrame();
        });

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Basic Geometry Designer!");
        System.out.println("--------------------------------------");
        System.out.println("Available commands:");
        System.out.println("  2D Shapes:");
        System.out.println("    'create circle radius <value> at <x> <y>'");
        System.out.println("      Example: 'create circle radius 50 at 0 0'");
        System.out.println("    'create square side <value> at <x> <y>'");
        System.out.println("      Example: 'create square side 100 at -50 50'");
        System.out.println("    'create triangle vertices <x1> <y1> <x2> <y2> <x3> <y3>'");
        System.out.println("      Example: 'create triangle vertices -100 -50 100 -50 0 100'");
        System.out.println("    'create polygon sides <n> radius <r> at <x> <y>' (n from 3 to 10)");
        System.out.println("      Example: 'create polygon sides 5 radius 80 at 0 0'");
        System.out.println("  3D Shapes (Wireframe Projection):");
        System.out.println("    'create cube side <s> at <x> <y> <z>'");
        System.out.println("      Example: 'create cube side 100 at 0 0 0'");
        System.out.println("    'create sphere radius <r> at <x> <y> <z>'");
        System.out.println("      Example: 'create sphere radius 70 at 150 150 50'");
        System.out.println("    'create cylinder radius <r> height <h> at <x> <y> <z>'");
        System.out.println("      Example: 'create cylinder radius 40 height 120 at -150 -150 -50'");
        System.out.println("  Utility Commands:");
        System.out.println("    'clear' (to clear the visualizer window)");
        System.out.println("    'exit' or 'quit' (to close the application)");
        System.out.println("--------------------------------------");
        System.out.println("Enter your command:");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("exit") || command.equals("quit")) {
                System.out.println("Exiting the Basic Geometry Designer. Goodbye!");
                if (visualizer != null) {
                    SwingUtilities.invokeLater(() -> visualizer.dispose());
                }
                break;
            } else if (command.equals("clear")) {
                if (visualizer != null) {
                    SwingUtilities.invokeLater(() -> visualizer.clearAllShapes());
                }
                System.out.println("Visualizer cleared.");
                continue;
            }

            // Attempt to create a 2D shape
            Shape generated2DShape = null;
            // Attempt to create a 3D shape
            _3DShape generated3DShape = null;

            if (command.startsWith("create circle")) {
                generated2DShape = createCircleFromCommand(command);
            } else if (command.startsWith("create square")) {
                generated2DShape = createSquareFromCommand(command);
            } else if (command.startsWith("create triangle")) {
                generated2DShape = createTriangleFromCommand(command);
            } else if (command.startsWith("create polygon")) { // New 2D shape
                generated2DShape = createPolygonFromCommand(command);
            }
            // 3D Shapes
            else if (command.startsWith("create cube")) { // New 3D shape
                generated3DShape = createCubeFromCommand(command);
            } else if (command.startsWith("create sphere")) { // New 3D shape
                generated3DShape = createSphereFromCommand(command);
            } else if (command.startsWith("create cylinder")) { // New 3D shape
                generated3DShape = createCylinderFromCommand(command);
            }
            else {
                System.out.println("Sorry, I don't understand that command. Please try again.");
            }

            if (generated2DShape != null) {
                System.out.println("Generated: " + generated2DShape.getRepresentation());
                if (visualizer != null) {
                    final Shape shapeToDraw = generated2DShape;
                    SwingUtilities.invokeLater(() -> visualizer.addShape2D(shapeToDraw));
                }
            } else if (generated3DShape != null) {
                System.out.println("Generated: " + generated3DShape.getRepresentation());
                if (visualizer != null) {
                    final _3DShape shapeToDraw = generated3DShape;
                    SwingUtilities.invokeLater(() -> visualizer.addShape3D(shapeToDraw));
                }
            }
        }
        scanner.close();
    }

    // --- Existing 2D Shape Creation Helper Methods (unchanged) ---
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
            if (radius <= 0) {
                 System.err.println("Circle radius must be positive.");
                 return null;
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

    private static Square createSquareFromCommand(String command) {
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

    private static Triangle createTriangleFromCommand(String command) {
        try {
            String[] parts = command.split(" ");
            int verticesIndex = -1;
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("vertices")) {
                    verticesIndex = i;
                    break;
                }
            }

            if (verticesIndex != -1 && parts.length >= verticesIndex + 7) {
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

    // --- New Shape Creation Helper Methods ---

    private static Polygon createPolygonFromCommand(String command) {
        // Expected format: "create polygon sides <n> radius <r> at <x> <y>"
        try {
            String[] parts = command.split(" ");
            int numSides = 0;
            double radius = 0;
            double centerX = 0;
            double centerY = 0;

            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("sides") && i + 1 < parts.length) {
                    numSides = Integer.parseInt(parts[i+1]);
                } else if (parts[i].equals("radius") && i + 1 < parts.length) {
                    radius = Double.parseDouble(parts[i+1]);
                } else if (parts[i].equals("at") && i + 2 < parts.length) {
                    centerX = Double.parseDouble(parts[i+1]);
                    centerY = Double.parseDouble(parts[i+2]);
                }
            }

            if (numSides < 3 || numSides > 10) {
                System.err.println("Polygon must have between 3 and 10 sides.");
                return null;
            }
            if (radius <= 0) {
                System.err.println("Polygon radius must be positive.");
                return null;
            }
            return new Polygon(numSides, centerX, centerY, radius);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numbers for polygon. Please check your input format: 'create polygon sides <n> radius <r> at <x> <y>'");
            return null;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while creating a polygon: " + e.getMessage());
            return null;
        }
    }

    private static Cube createCubeFromCommand(String command) {
        // Expected format: "create cube side <s> at <x> <y> <z>"
        try {
            String[] parts = command.split(" ");
            double side = 0;
            double x = 0, y = 0, z = 0;

            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("side") && i + 1 < parts.length) {
                    side = Double.parseDouble(parts[i+1]);
                } else if (parts[i].equals("at") && i + 3 < parts.length) {
                    x = Double.parseDouble(parts[i+1]);
                    y = Double.parseDouble(parts[i+2]);
                    z = Double.parseDouble(parts[i+3]);
                }
            }
            if (side <= 0) {
                System.err.println("Cube side length must be positive.");
                return null;
            }
            return new Cube(x, y, z, side);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numbers for cube. Please check your input format: 'create cube side <s> at <x> <y> <z>'");
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while creating a cube: " + e.getMessage());
            return null;
        }
    }

    private static Sphere createSphereFromCommand(String command) {
        // Expected format: "create sphere radius <r> at <x> <y> <z>"
        try {
            String[] parts = command.split(" ");
            double radius = 0;
            double x = 0, y = 0, z = 0;

            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("radius") && i + 1 < parts.length) {
                    radius = Double.parseDouble(parts[i+1]);
                } else if (parts[i].equals("at") && i + 3 < parts.length) {
                    x = Double.parseDouble(parts[i+1]);
                    y = Double.parseDouble(parts[i+2]);
                    z = Double.parseDouble(parts[i+3]);
                }
            }
            if (radius <= 0) {
                System.err.println("Sphere radius must be positive.");
                return null;
            }
            return new Sphere(x, y, z, radius);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numbers for sphere. Please check your input format: 'create sphere radius <r> at <x> <y> <z>'");
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while creating a sphere: " + e.getMessage());
            return null;
        }
    }

    private static Cylinder createCylinderFromCommand(String command) {
        // Expected format: "create cylinder radius <r> height <h> at <x> <y> <z>"
        try {
            String[] parts = command.split(" ");
            double radius = 0;
            double height = 0;
            double x = 0, y = 0, z = 0;

            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("radius") && i + 1 < parts.length) {
                    radius = Double.parseDouble(parts[i+1]);
                } else if (parts[i].equals("height") && i + 1 < parts.length) {
                    height = Double.parseDouble(parts[i+1]);
                } else if (parts[i].equals("at") && i + 3 < parts.length) {
                    x = Double.parseDouble(parts[i+1]);
                    y = Double.parseDouble(parts[i+2]);
                    z = Double.parseDouble(parts[i+3]);
                }
            }
            if (radius <= 0 || height <= 0) {
                System.err.println("Cylinder radius and height must be positive.");
                return null;
            }
            return new Cylinder(x, y, z, radius, height);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numbers for cylinder. Please check your input format: 'create cylinder radius <r> height <h> at <x> <y> <z>'");
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while creating a cylinder: " + e.getMessage());
            return null;
        }
    }
}