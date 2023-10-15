public class OffByN implements CharacterComparator {
    private final int diff;

    /** Constructor
     *
     * @param n the accepted difference
     */
    public OffByN(int n) {
        diff = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int cmp = x - y;
        return cmp == diff || cmp == - diff;
    }
}
