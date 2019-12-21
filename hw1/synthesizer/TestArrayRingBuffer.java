package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(8);
        for(int i = 0; i <8; i++){
        	arb.enqueue(2);
        }
        arb.dequeue();

        arb.enqueue(2);
        for(int i = 0; i <8; i++){
        	arb.dequeue();
        }
        // assertEquals((Integer) 1, (Integer) arb.last);
        // assertEquals((Integer) 1, (Integer) arb.first);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
