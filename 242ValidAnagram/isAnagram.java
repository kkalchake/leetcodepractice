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