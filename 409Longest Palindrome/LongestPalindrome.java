  public class Solution {
    private static final int ASCII_ALPHABET_SIZE = 128;

    public int longestPalindrome(String text) {
      if (text == null || text.isEmpty()) {
        return 0;
      }
      int[] charFrequencies = countCharacterFrequencies(text);
      return calculatePalindromeLength(charFrequencies);
    }

    private int[] countCharacterFrequencies(String text) {
      int[] frequencies = new int[ASCII_ALPHABET_SIZE];
      for (char character : text.toCharArray()) {
        frequencies[character]++;
      }
      return frequencies;
    }

    private int calculatePalindromeLength(int[] charFrequencies) {
      int length = 0;
      boolean hasOddFrequency = false;
      for (int count : charFrequencies) {
        if (isEven(count)) {
          length += count;
        } else {
          length += count - 1;
          hasOddFrequency = true;
        }
      }
      if (hasOddFrequency) {
        length++;
      }
      return length;
    }

    private boolean isEven(int count) {
      return count % 2 == 0;
    }
  }