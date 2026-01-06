# Intuition
When looking at this problem, my first thought is the Sorting Approach: convert both strings to arrays, sort them alphabetically, and check if they match. It’s intuitive and it works. However, in a production environment with large inputs (for example 50,000 characters) sorting is actually quite 'expensive'.

# Approach
Sorting requires $O(n \log n)$ time and forces the computer to do a lot of extra work—organizing every single letter just to see if the counts match. It also creates extra copies of the strings in memory.

Instead, I’m opting for a Frequency Counter approach. I think of this like a row of 26 buckets—one for each letter of the alphabet. By walking through the strings just once, I can 'tally' the letters. It’s faster, it uses almost no extra memory, and it gets us the answer in a single pass.

In Java, every letter has a hidden numeric value. By subtracting the value of the letter 'a' from any character, we essentially transform the alphabet into a simple index from 0 to 25. This allows us to use a standard array as a high-speed scoreboard. Instead of searching for where 'c' belongs, the computer knows instantly to go to index 2.

Once we’ve finished our tally, we perform one last scan of our 'scoreboard.' The moment we find a number that isn't zero, we know the strings aren't anagrams and we break out immediately. We don't waste time checking the rest of the alphabet if we've already found a discrepancy.

# Complexity
- Time complexity:
$O(n)$
The algorithm walks through the strings once ($O(n)$) and then checks a fixed-size array of 26 elements ($O(1)$).

- Space complexity:
$O(1)$
We only use a fixed-size integer array of 26 slots, regardless of how large the input strings are.

# Code
```java []
class Solution {
    public boolean isAnagram(String s, String t) {
        // 1. Prepare strings for comparison
        String cleanS = cleanString(s);
        String cleanT = cleanString(t);

        // 2. Validate strings with given constraints
        validate(cleanS, cleanT);

        // 3. Quick Check: Anagrams MUST be the same length
        if (cleanS.length() != cleanT.length()) {
            return false;
        }

        // 4. Comparison using an indexed array (the 26 letters of the alphabet)
        int[] charCounts = new int[26];

        for (int i = 0; i < cleanS.length(); i++) {
            // Increment for s, Decrement for t
            charCounts[cleanS.charAt(i) - 'a']++;
            charCounts[cleanT.charAt(i) - 'a']--;
        }

        // 5. Early Break: If any count is not zero, they are not identical
        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    private String cleanString(String input) {
        if (input == null) return "";
        return input.replace(" ", "");
    }

    private void validate(String s, String t) {
        int maxLen = 50000;

        // Check Length Constraints
        if (s.length() < 1 || s.length() > maxLen || t.length() < 1 || t.length() > maxLen) {
            throw new IllegalArgumentException("Invalid Length: Strings must be between 1 and " + maxLen + " characters (excluding spaces).");
        }

        // Check Character Constraints
        if (!s.matches("[a-z]+") || !t.matches("[a-z]+")) {
            throw new IllegalArgumentException("Invalid Characters: Input must contain only lowercase English letters.");
        }
    }
}
```