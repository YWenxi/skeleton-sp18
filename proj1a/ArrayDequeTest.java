public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        boolean flag = ad.isEmpty();
        ad.addLast(0);
        ad.addLast(1);
        ad.removeFirst();
        ad.removeLast();
        ad.addLast(4);
        int out = ad.get(0);
    }
}
