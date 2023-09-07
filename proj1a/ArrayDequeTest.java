public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        ad.addFirst(0);
        ad.addFirst(1);
        ad.addFirst(2);

        ad.addLast(-1);
        ad.addLast(-2);
        ad.addLast(-3);
        ad.addLast(-4);
        ad.addLast(-5);
        ad.addLast(-6);
        ad.get(2);

        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
    }
}
