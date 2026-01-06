import re

class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        # 1. Prepare strings (Reusable logic)
        clean_s = self._clean_string(s)
        clean_t = self._clean_string(t)

        # 2. Validate strings with given constraints
        self._validate(clean_s, clean_t)

        # 3. Quick Check: Anagrams MUST be the same length
        if len(clean_s) != len(clean_t):
            return False

        # 4. Comparison using an indexed list (the 26 letters of the alphabet)
        # In Python, [0] * 26 creates a list of 26 zeros
        char_counts = [0] * 26

        # 5. The Tally Loop
        for i in range(len(clean_s)):
            # Identify characters
            char_from_s = clean_s[i]
            char_from_t = clean_t[i]

            # Map characters to list indices 0-25 using ord()
            # ord('a') gives the ASCII integer value of 'a'
            index_s = ord(char_from_s) - ord('a')
            index_t = ord(char_from_t) - ord('a')

            # Increment for s, Decrement for t
            char_counts[index_s] += 1
            char_counts[index_t] -= 1

        # 6. Early Break: If any count is not zero, they are not identical
        for count in char_counts:
            if count != 0:
                return False

        return True

    # Helper method (denoted by _ prefix in Python)
    def _clean_string(self, input_str: str) -> str:
        if input_str is None:
            return ""
        return input_str.replace(" ", "")

    def _validate(self, s: str, t: str) -> None:
        max_len = 50000

        # Check Length Constraints
        if not (1 <= len(s) <= max_len) or not (1 <= len(t) <= max_len):
            raise ValueError(f"Invalid Length: Strings must be between 1 and {max_len} characters (excluding spaces).")

        # Check Character Constraints using Regex
        # re.fullmatch ensures the entire string matches the pattern
        if not re.fullmatch(r"[a-z]+", s) or not re.fullmatch(r"[a-z]+", t):
            raise ValueError("Invalid Characters: Input must contain only lowercase English letters.")