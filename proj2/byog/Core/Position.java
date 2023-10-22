package byog.Core;

import byog.TileEngine.TETile;

import java.util.Comparator;

public class Position implements Comparable<Position> {
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

    @Override
    public int compareTo(Position p) {
        int flag = -1;
        if (x <= p.x && y <= p.y) {
            flag = 1;
        }
        if (x == p.x && y == p.y) {
            flag = 0;
        }
        return flag;
    }
}
