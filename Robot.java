public class Robot {
    int id;
    int row;
    int col;

    public Robot(int id) {
        this.id = id;
        this.row = 0; // Default starting point
        this.col = 0;
    }

    public String getPosition() {
        return "(" + row + "," + col + ")";
    }
}
