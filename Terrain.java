import java.util.*;

public class Terrain {
    private int rows;
    private int cols;
    private Map<Integer, Robot> robots; // Mapping robot id based on robot object //  Map is a Java interface, which is used to map key-pair values
    private boolean[][] gridOccupied;

    public Terrain(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.robots = new HashMap<>(); // HAshMap contains null values and keys
        this.gridOccupied = new boolean[rows][cols]; // True - robot is currently in position // False - cell is free
    }

    public void addRobot(int robotId) {
        Robot robot = new Robot(robotId);
        robots.put(robotId, robot);
        gridOccupied[0][0] = true;
    }

    public String getRobotPosition(int robotId) {
        Robot r = robots.get(robotId);
        return r.getPosition();
    }

    public void moveRobot(int robotId, String command) {
        Robot robot = robots.get(robotId);
        int row = robot.row;
        int col = robot.col;

        char direction = command.charAt(0);
        int steps = Character.getNumericValue(command.charAt(1));

        // Unmark current cell
        gridOccupied[row][col] = false;

        for (int i = 0; i < steps; i++) {
            int newRow = row;
            int newCol = col;

            switch (direction) {
                case 'N': newRow--; break;
                case 'S': newRow++; break;
                case 'E': newCol++; break;
                case 'W': newCol--; break;
                default:
                    System.out.println("Invalid direction.");
                    gridOccupied[row][col] = true; // re-mark
                    return;
            }

            // Bounds check
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                System.out.println("Reached edge of terrain. Movement stopped.");
                break;
            }

            // Collision check
            if (isCellOccupied(newRow, newCol)) {
                System.out.println("Another robot is at (" + newCol + "," + newRow + "). Movement stopped.");
                break;
            }

            row = newRow;
            col = newCol;
        }

        // Final position
        robot.row = row;
        robot.col = col;
        gridOccupied[row][col] = true;
    }

    private boolean isCellOccupied(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && gridOccupied[row][col];
    }
}
