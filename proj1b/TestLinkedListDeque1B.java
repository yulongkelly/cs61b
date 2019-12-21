import static org.junit.Assert.*;
import org.junit.Test;

public class TestLinkedListDeque1B {

	@Test
	public void testAddLast() {
		StudentLinkedListDeque<Integer> sad1 = new StudentLinkedListDeque<Integer>();
		sad1.addLast(5);
        sad1.addLast(10);
		StudentLinkedListDeque<Integer> exp = new StudentLinkedListDeque<Integer>();
		exp.addLast(5);
		exp.addLast(10);
		assertEquals(exp, sad1);
	}

	public static void main(String... args) {        
        jh61b.junit.TestRunner.runTests("failed", TestLinkedListDeque1B.class);
    }
}