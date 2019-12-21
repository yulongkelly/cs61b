import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {

	@Test
	public void testAddLast() {
		StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
		sad1.addLast(5);
        sad1.addLast(10);
		ArrayDequeSolution<Integer> exp = new ArrayDequeSolution<Integer>();
		exp.addLast(5);
		exp.addLast(10);
		for(int i=0; i<exp.size(); i++){
			assertEquals(exp.items[i], sad1.items[i]);
		}
	}

	public static void main(String... args) {        
        jh61b.junit.TestRunner.runTests("failed", TestArrayDeque1B.class);
    }
}