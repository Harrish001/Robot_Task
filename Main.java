import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Terrain terrain = new Terrain(5, 5); // 10x10 grid

        System.out.print("Enter number of robots: ");
        int robotCount = scanner.nextInt();

        for (int i = 1; i <= robotCount; i++) {
            terrain.addRobot(i);
            System.out.println("Robot " + i + " added at position (0,0)");
        }

        String continueInput = "yes";
        while (continueInput.equalsIgnoreCase("yes")) {
            System.out.print("\nEnter robot ID to move: ");
            int robotId = scanner.nextInt();

            System.out.print("Enter movement command (e.g., N3, E2): ");
            String command = scanner.next();

            terrain.moveRobot(robotId, command);
            System.out.println("Robot " + robotId + " new position: " + terrain.getRobotPosition(robotId));

            System.out.print("Do you want to move another robot? (yes/no): ");
            continueInput = scanner.next();
        }

        // Display final positions of all robots
        System.out.println("\nFinal positions:");
        for (int i = 1; i <= robotCount; i++) {
            System.out.println("Robot " + i + ": " + terrain.getRobotPosition(i));
        }

        scanner.close();
    }
}
