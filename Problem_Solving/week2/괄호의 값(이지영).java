package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BJ2504 {
  static Map<String, Character> oppsite;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();

    oppsite = new HashMap<>();

    oppsite.put("(", ')');
    oppsite.put(")", '(');
    oppsite.put("[", ']');
    oppsite.put("]", '[');

    System.out.println(solution(str));

  }

  public static int solution(String str) {
    int total = 0;
    List<String> stack = new ArrayList<>();

    for (int index = str.length() - 1; index >= 0; index--) {
      char c = str.charAt(index);

      if (c == ')' || c == ']') {
        stack.add(Character.toString(c));
        continue;
      }

      int partial = getPartialSum(stack);

      if (stack.isEmpty() || (!stack.isEmpty() && oppsite.get(stack.get(stack.size() - 1)) != c))
        return 0;

      String last = stack.remove(stack.size() - 1);

      // 반드시 마지막 값은 is not numeric함을 보장
      if (last.equals(")"))
        stack.add(String.valueOf(partial * 2));
      else
        stack.add(String.valueOf(partial * 3));

    }

    while (!stack.isEmpty()) {
      String val = stack.get(stack.size() - 1);
      if (!isNumeric(val))
        return 0;
      total += Integer.parseInt(stack.remove(stack.size() - 1));
    }

    return total;
  }

  public static int getPartialSum(List<String> stack) {
    int partialSum = 0;

    while (!stack.isEmpty() && isNumeric(stack.get(stack.size() - 1))) {
      partialSum += Integer.parseInt(stack.remove(stack.size() - 1));
    }

    if (partialSum == 0)
      return 1;
    return partialSum;

  }

  public static boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }
}
