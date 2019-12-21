public class Palindrome {
	/** build a Deque where the characters in the deque appear in 
	  * the same order as in the word.*/
	public static Deque<Character> wordToDeque(String word) {
		Deque<Character> charArray = new LinkedListDeque<Character>();
		for(int i=0; i<word.length(); i++){
			charArray.addLast(word.charAt(i));
		}
		return charArray;
	}

	
	private static boolean isPalindrome(Deque<Character> x) {
        if (x.size() == 0 || x.size() == 1) {
            return true;
        }

        char first = x.removeFirst();
        char last = x.removeLast();

        boolean isPal = first == last;
        if (isPal) {
            return isPalindrome(x) && isPal;
        } else {
            return false;
        }
    }
    /* return true if the given word is a palindrome, and false otherwise.*/
    public static boolean isPalindrome(String word) {
        word = word.toLowerCase();      // Ideally this should be here. But not mentioned in spec
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque);
    }

    /*The method will return true if the word is a palindrome according to the character
     * comparison test provided by the CharacterComparator passed in as argument cc. */
    public static boolean isPalindrome(String word, CharacterComparator cc) {
    	word = word.toLowerCase();      // Ideally this should be here. But not mentioned in spec
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque, cc);
    }

    /** This method will return true if the reversed deque is exactly
      * off by one or off by N excepter the middle char in odd length */
    public static boolean isPalindrome(Deque<Character> deque, CharacterComparator cc) {
    	if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }

        char first = deque.removeFirst();
        char last = deque.removeLast();

        boolean isPal = cc.equalChars(first, last);
        if (isPal) {
            return isPalindrome(deque, cc) && isPal;
        } else {
            return false;
        }
    }


	public static void main(String[] args) {
		wordToDeque("haha");
		if(isPalindrome("haha")) {
			System.out.println("haha");
		}
		if(isPalindrome("racecar")) {
			System.out.println("racecar");
		}
		OffByN offby2 = new OffByN(2);
		if(isPalindrome("abmqodc", offby2)) {
			System.out.println("abmqodc");
		}

	}
}