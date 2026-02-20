class Solution {
    public int reverseBits(int n) {
        String converted_binary_string = ConvertOriginalIntToReversedBinaryString(n);
        int result_integer = ConvertReversedBinaryStringToResultInteger(converted_binary_string);
        return result_integer;
    }
    public String ConvertOriginalIntToReversedBinaryString(int original_input) {
        String converted_binary_string = "";
        for (int i = 0; i < 32; i++) {
            if (original_input % 2 == 0) {
                converted_binary_string = "0" + converted_binary_string;
            } else {
                converted_binary_string = "1" + converted_binary_string;
            }
            original_input = original_input / 2;
        }
        return converted_binary_string;
      }
    public int ConvertReversedBinaryStringToResultInteger(String converted_binary_string) {
        int result_integer = 0;
        for (int i = 0; i < converted_binary_string.length(); i++) {
            if (converted_binary_string.charAt(i) == '1') {
                result_integer = (int)Math.pow(2, i) + result_integer;
            }
        }
        return result_integer;
      }
}

