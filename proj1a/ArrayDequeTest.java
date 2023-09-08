public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        boolean flag = ad.isEmpty();
        for (int i = 0; i < 8; i++) {
            ad.addLast(i);
        }
        int out = ad.removeLast();
        System.out.println(ad.removeFirst());
        System.out.println(ad.removeLast());
    }
}
