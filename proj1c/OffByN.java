public class OffByN implements CharacterComparator {

	public int num;
	/* constructor */
	public OffByN(int N) {
		num = N;
	}

	@Override
	 public boolean equalChars(char x, char y){
		int difference = Math.abs(x - y);
		if(difference == num) {
			return true;
		}
		else {
			return false;
		}
	}
}