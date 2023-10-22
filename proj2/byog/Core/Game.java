package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import byog.TileEngine.Tileset;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        // parse the string
        String regex = "([a-zA-Z]?)(\\d*)([a-zA-Z]?)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String enterCommand;
        long seed;
        String restCommand;
        TETile[][] finalWorldFrame = null;

        if (matcher.matches()) {
            enterCommand = matcher.group(1).toLowerCase();
            String seedString = matcher.group(2);
            restCommand = matcher.group(3).toLowerCase();

            // Parse seedString as a long integer
            seed = seedString.isEmpty() ? 0 : Long.parseLong(seedString);
        } else {
            throw new InputMismatchException("Not a correct input.");
        }

        if (enterCommand.equals("n")) {
            // initialization
            finalWorldFrame = initWorld();
            ter.initialize(WIDTH, HEIGHT);

            // generate rooms
            getRandomRooms(finalWorldFrame, seed);

            // render and return the final frame
            ter.renderFrame(finalWorldFrame);
        }

        return finalWorldFrame;
    }

    /** initialize the empty world. Each element in the array is set to `Tileset.NOTHING` */
    private TETile[][] initWorld() {
        TETile[][] worldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                worldFrame[x][y] = Tileset.NOTHING;
            }
        }
        return worldFrame;
    }

    /** add a row of some tiles to the world
     *
     * @param worldFrame world
     * @param start starting position
     * @param length length of the row
     * @param t which tileset to render
     */
    private static void addRow(TETile[][] worldFrame, Position start, int length, TETile t) {
        for (int xi = 0; xi < length; xi++) {
            int xCoord = start.x + xi;
            int yCoord = start.y;
            worldFrame[xCoord][yCoord] = t;
        }
    }

    /** add a row from start to end (included) */
    private static void addRow(TETile[][] worldFrame, Position start, Position end, TETile t) {
        if (start.y != end.y) {
            throw new IllegalArgumentException("Not in the same row! %s and %s".formatted(start, end));
        }
        addRow(worldFrame, new Position(Math.min(start.x, end.x), start.y), Math.abs(end.x - start.x) + 1, t);
    }

    /** add a column of some tiles to the world
     *
     * @param worldFrame world
     * @param start starting position
     * @param length length of the column
     * @param t which tileset to render
     */
    private static void addCol(TETile[][] worldFrame, Position start, int length, TETile t) {
        for (int yi = 0; yi < length; yi++) {
            int xCoord = start.x;
            int yCoord = start.y + yi;
            worldFrame[xCoord][yCoord] = t;
        }
    }

    /** add a column from start to end (included) */
    private static void addCol(TETile[][] worldFrame, Position start, Position end, TETile t) {
        if (start.x != end.x) {
            throw new IllegalArgumentException("Not in the same column, i.e. x-index! %s and %s".formatted(start, end));
        }
        addCol(worldFrame, new Position(start.x, Math.min(start.y, end.y)), Math.abs(end.y - start.y) + 1, t);
    }

    /** check if the position is valid in the current worldFrame */
    private static boolean validPosition(Position p) {
        return p.x >= WIDTH || p.y >= HEIGHT || p.x < 0 || p.y < 0;
    }


    private void addWall(TETile[][] worldFrame, Position center, Random RANDOM) {
        int cx = center.x;
        int cy = center.y;
        if (worldFrame[cx][cy] != Tileset.FLOOR) {
            return;
        }
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                if (worldFrame[cx + dx][cy + dy] == Tileset.NOTHING) {
                    worldFrame[cx + dx][cy + dy] = TETile.colorVariant(Tileset.WALL, 32, 32, 32, RANDOM);
                }
            }
        }
    }

    private void addHallway(TETile[][] worldFrame, Position start, Position end) {
        Position turnPoint = new Position(start.x, end.y);
        addRow(worldFrame, turnPoint, end, Tileset.FLOOR);
        addCol(worldFrame, start, turnPoint, Tileset.FLOOR);
    }

    private void addDoor(TETile[][] worldFrame, Random RANDOM) {
        int px = -1;
        int py = -1;
        boolean flag = false;
        while (!flag) {
            px = RandomUtils.uniform(RANDOM, 1, WIDTH - 1);
            py = RandomUtils.uniform(RANDOM, 1, HEIGHT - 1);
            // System.out.printf("(%d, %d)%n", px, py);
            if (worldFrame[px][py] == Tileset.NOTHING || worldFrame[px][py] == Tileset.FLOOR) {
                continue;
            }
            if (worldFrame[px + 1][py] == Tileset.FLOOR) {
                flag = true;
            }
            if (worldFrame[px - 1][py] == Tileset.FLOOR) {
                flag = true;
            }
            if (worldFrame[px][py + 1] == Tileset.FLOOR) {
                flag = true;
            }
            if (worldFrame[px][py - 1] == Tileset.FLOOR) {
                flag = true;
            }
        }
        worldFrame[px][py] = Tileset.LOCKED_DOOR;
    }

    private void getRandomRooms(TETile[][] worldFrame, long seed) {
        final Random RANDOM = new Random(seed);

        final int numRooms = RandomUtils.uniform(RANDOM, 10,  30);

        Position hallwayStart = null;
        Position hallwayEnd;

        for (int i = 0; i < numRooms; i++) {
            RectangleRoom newRoom = new RectangleRoom(RANDOM, worldFrame);

            // build hallways
            if (hallwayStart == null) {
                hallwayStart = newRoom.getRandomInnerSpace(RANDOM);
                continue;
            } else {
                hallwayEnd = newRoom.getRandomInnerSpace(RANDOM);
                addHallway(worldFrame, hallwayStart, hallwayEnd);
            }
        }

        // build walls
        for (int xi = 0; xi < WIDTH; xi++) {
            for (int yi = 0; yi < HEIGHT; yi++) {
                addWall(worldFrame, new Position(xi, yi), RANDOM);
            }
        }

        // add a door
        addDoor(worldFrame, RANDOM);
    }

    // private room class
    private static class RectangleRoom {
        // positions for left bottom inside corner and right upper inside corner
        /* An example
            y
            ^
                              ur
            [][][][][][][][][]
            []              []
            bl[][][][][][][][] --> x
         */
        Position bl;
        Position ur;
        TETile[][] worldFrame;
        private int height;
        private int width;

        // whether the room is connected to the main room

        RectangleRoom(Position bottomLeft, Position upperRight, TETile[][] worldFrame) {
            bl = bottomLeft;
            ur = upperRight;
            this.worldFrame = worldFrame;
            initialize();
        }

        RectangleRoom(Random RANDOM, TETile[][] worldFrame) {
            final int maxSize = 5;
            bl = new Position(
                    RandomUtils.uniform(RANDOM, 1, WIDTH - 1),
                    RandomUtils.uniform(RANDOM, 1, HEIGHT - 1)
            );
            ur = new Position(
                    Math.min(RandomUtils.uniform(RANDOM, bl.x + 1, WIDTH), bl.x + maxSize),
                    Math.min(RandomUtils.uniform(RANDOM, bl.y + 1, HEIGHT), bl.y + maxSize)
            );
            this.worldFrame = worldFrame;
            initialize();
        }

        private void initialize() {
            width = ur.x - bl.x;
            height = ur.y - bl.y;

            // check validity
            if (ur.x - bl.x < 1 && ur.y - bl.y < 1) {
                throw new IllegalArgumentException("No empty space inside the room.");
            }
            if (validPosition(bl) || validPosition(new Position(ur.x - 1, ur.y - 1))) {
                throw new IllegalArgumentException("Positions are not inside the world. %s and %s".formatted(bl, ur));
            }
            // update the world frame
            // inner space: FLOOR
            for (int iRow = 0; iRow < height; iRow++) {
                addRow(worldFrame, new Position(bl.x, bl.y + iRow), width, Tileset.FLOOR);
            }
        }

        /** get the position of one of the inner space randomly */
        public Position getRandomInnerSpace(Random RANDOM) {
            return new Position(
                    RandomUtils.uniform(RANDOM, bl.x, ur.x),
                    RandomUtils.uniform(RANDOM, bl.y, ur.y)
            );
        }
    }
}
