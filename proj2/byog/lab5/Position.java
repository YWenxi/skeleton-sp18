package byog.lab5;

public class Position {
    int x;
    int y;

    Position(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
