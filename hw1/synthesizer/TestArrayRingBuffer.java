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

        int currentSize = 10;

        for (int i = 0; i < 5; i++) {
            assertEquals("Dequeue Check", arb.dequeue(), (Integer) i);
            currentSize -= 1;
            assertEquals("Size Check", currentSize, arb.fillCount());
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
