class Solution {
    public boolean backspaceCompare(String input1, String input2) {
    int indx_input1 = input1.length() - 1;
    int indx_input2 = input2.length() - 1;
    while (indx_input1 >= 0 || indx_input2 >= 0) {
      indx_input1 = findValidIndexForComparison(input1, indx_input1);
      indx_input2 = findValidIndexForComparison(input2, indx_input2);

      if ((indx_input1 >= 0) && (indx_input2 >= 0) && ((input1.charAt(indx_input1)) != (input2.charAt(indx_input2)))) {
        return false;
      }
      if ((indx_input1 >= 0) != (indx_input2 >= 0)) {
        return false;
      }
      indx_input1--;
      indx_input2--;
    } return true;
    }
  public static int findValidIndexForComparison(String input, int i) {
    int skip_char = 0;
    while (i >= 0) {
      if (input.charAt(i) == '#') {
        skip_char++;
        i--;
      } else if (skip_char > 0) {
        skip_char--;
        i--;
      }
      else
        break;
    }
    return i;
  }
  }
