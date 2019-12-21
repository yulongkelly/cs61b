import static org.junit.Assert.*;
import org.junit.Test;

public class TesLinkedListDeque1B {

	@Test
	public void testAddLast() {
		StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
		sad1.addLast(5);
        sad1.addLast(10);
		StudentArrayDeque<Integer> exp = new StudentArrayDeque<Integer>();
		exp.addLast(5);
		exp.addLast(10);
		assertEquals(exp, sad1);
	}

	public static void main(String... args) {        
        jh61b.junit.TestRunner.runTests("failed", TestArrayDeque1B.class);
    }
}