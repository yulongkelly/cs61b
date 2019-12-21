public class offByOne implements CharacterComparator {


	@Override
	public boolean equalChars(char x, char y) {
		int difference = abs(x - y);
		if(difference == 1) {
			return true;
		}
		else {
			return false;
		}
	}

}