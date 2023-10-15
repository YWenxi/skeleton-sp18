public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int cmp = y - x;
        return cmp == 1 || cmp == -1;
    }
}
