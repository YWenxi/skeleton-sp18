public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    /** Private helper function to check if a word deque is a palindrome.
     * @param word to check
     * @return true if it is a palindrome
     */
    private boolean isPalindrome(Deque<Character> word) {
        // check basic scenario
        if (word.isEmpty()) {
            return true;
        }
        if (word.size() == 1) {
            return true;
        }

        Character first = word.removeFirst();
        Character last = word.removeLast();
        if (first != last) {
            return false;
        } else {
            return isPalindrome(word);
        }
    }

    /** Check if the word is a palindrome
     *
     * @param word to check
     * @return true if it is
     */
    public boolean isPalindrome(String word) {
        Deque<Character> candidate = wordToDeque(word);

        return isPalindrome(candidate);
    }

    /** General palindrome checker
     *
     * @param word to check
     * @param cc a general character comparator
     * @return true if it is
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> candidate = wordToDeque(word);

        return isPalindrome(candidate, cc);
    }

    /** General helper function for palindrome checker
     *
     * @param word to check
     * @param cc general comparator
     * @return true if it is
     */
    private boolean isPalindrome(Deque<Character> word, CharacterComparator cc) {
        if (word.isEmpty() || word.size() == 1) {
            return true;
        }

        Character first = word.removeFirst();
        Character last = word.removeLast();
        if (!cc.equalChars(first, last)) {
            return false;
        } else {
            return isPalindrome(word, cc);
        }
    }
}
