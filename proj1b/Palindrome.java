public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    /** Check if the word is a palindrome
     *
     * @param word
     * @return ture if it is
     */
    public boolean isPalindrome(String word) {
        Deque<Character> candidate = wordToDeque(word);

        // trivial case
        if (candidate.isEmpty()) {
            return true;
        }

        // iterate
        int size = candidate.size();
        for (int i = 0; i < size; i++) {
            Character cHead = candidate.get(i);
            Character cTail = candidate.get(size - i - 1);
            if (cHead != cTail) {
                return false;
            }
        }
        return true;
    }
}
