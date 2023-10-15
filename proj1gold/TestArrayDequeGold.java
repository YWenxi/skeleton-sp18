import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    /** Test student's array deque implementation
     *
     * @source StudentArrayDequeLauncher
     */
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution1 = new ArrayDequeSolution<>();
        StringBuilder errMessage = new StringBuilder();

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                solution1.addLast(i);
                errMessage.append("addLast(").append(i).append(")\n");
;            } else {
                sad1.addFirst(i);
                solution1.addFirst(i);
                errMessage.append("addFirst(").append(i).append(")\n");
            }
        }

        // random remove
        for (int i = 0; i < 10; i++) {
            double randomBetweenZeroAndOne = StdRandom.uniform();

            Integer actual;
            Integer expected;

            if (randomBetweenZeroAndOne < 0.5) {
                actual = sad1.removeLast();
                expected = solution1.removeLast();
                errMessage.append("removeLast()\n");
            } else {
                actual = sad1.removeFirst();
                expected = solution1.removeFirst();
                errMessage.append("removeFirst()\n");
            }

            assertEquals(errMessage.toString(), expected, actual);
        }
    }
}
