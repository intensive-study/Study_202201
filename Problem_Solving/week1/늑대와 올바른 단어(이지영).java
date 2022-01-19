package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BJ13022 {

  static Map<Character, Character> NEXT;
  static char START = 'w';
  static char END = 'f';
  static String target;

  static int pointer = 0;
  static int prevCount = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    target = br.readLine();
    int result = 0;

    NEXT = new HashMap<>();
    NEXT.put('w', 'o');
    NEXT.put('o', 'l');
    NEXT.put('l', 'f');
    NEXT.put('f', 'w');

    if (target.charAt(0) == START && target.charAt(target.length() - 1) == END && target.length() > 4)
      result = run();
    System.out.println(result);
  }

  public static int run() {
    int res = 1;
    while (pointer < target.length()) {
      if (!Character.toString(target.charAt(pointer)).matches("[wolf]")) {
        res = 0;
        break;
      }

      if (target.charAt(pointer) == START) {
        prevCount = repeatValueBeEnd(pointer);
      } else {
        int temp = repeatValueBeEnd(pointer);
        if (prevCount != temp) {
          res = 0;
          break;
        }
      }
      pointer += prevCount;
      if (pointer < target.length() && NEXT.get(target.charAt(pointer - 1)) != target.charAt(pointer)) {
        res = 0;
        break;
      }

    }
    return res;
  }

  public static int repeatValueBeEnd(int p) {
    int valueToRepeat = target.charAt(p);
    int partialCount = 0;

    do {
      partialCount++;
    } while (p + partialCount < target.length() && target.charAt(p + partialCount) == valueToRepeat);

    return partialCount;
  }
}