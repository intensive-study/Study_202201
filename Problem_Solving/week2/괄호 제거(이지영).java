import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BJ2800 {
  static String str;

  // ')'의 인덱스로 해당 쌍이 몇번째 order인지 반환
  static Map<Integer, Integer> pair;
  static boolean[] include;
  static Set<String> already;
  static List<String> complated;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    str = br.readLine();

    pair = new HashMap<>();
    already = new HashSet<>();
    complated = new ArrayList<>();

    int pairCount = initialize();
    include = new boolean[pairCount];

    dfs(include, 0);

    Collections.sort(complated);

    for (String expression : complated) {
      System.out.println(expression);
    }

  }

  public static int initialize() {
    List<Integer> stack = new ArrayList<>();
    int order = 0;

    for (int index = 0; index < str.length(); index++) {
      if (str.charAt(index) == '(') {

        stack.add(order++);
        continue;
      }

      if (str.charAt(index) == ')') {
        int curOrder = stack.remove(stack.size() - 1);
        pair.put(index, curOrder);
      }
    }

    return order;

  }

  public static void dfs(boolean[] include, int count) {
    if (count == include.length) {
      if (getExpression(include).length() == str.length())
        return;

      String expression = getExpression(include);

      if (already.contains(expression))
        return;

      complated.add(expression);
      already.add(expression);
      return;
    }
    // 현재 괄호 포함
    include[count] = true;
    dfs(include, count + 1);

    // 현재 괄호 미포함
    include[count] = false;
    dfs(include, count + 1);

  }

  public static String getExpression(boolean[] include) {
    StringBuffer st = new StringBuffer();

    int count = 0;
    for (int index = 0; index < str.length(); index++) {
      if ((str.charAt(index) == '(') && !include[count++])
        continue;
      if (str.charAt(index) == ')' && !include[pair.get(index)])
        continue;

      st.append(str.charAt(index));
    }

    return st.toString();

  }
}
