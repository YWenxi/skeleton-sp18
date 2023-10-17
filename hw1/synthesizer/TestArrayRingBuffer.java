package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(10);

        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
            assertEquals("Size Check", i + 1, arb.fillCount());
        }

        try {
            arb.enqueue(10);
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e);
        }

        int currentSize = 10;

        for (int i = 0; i < 10; i++) {
            assertEquals("Dequeue Check", arb.dequeue(), (Integer) i);
            currentSize -= 1;
            assertEquals("Size Check", currentSize, arb.fillCount());
        }

        try {
            arb.dequeue();
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
