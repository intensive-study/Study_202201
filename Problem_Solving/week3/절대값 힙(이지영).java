package Problem_Solving.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

class Number implements Comparable<Number> {
  int num;

  Number(int num) {
    this.num = num;
  }

  @Override
  public int compareTo(Number o) {

    if (Math.abs(num) != Math.abs(o.num))
      return Math.abs(num) - Math.abs(o.num);
    return num - o.num;

  }
}

public class BJ11286 {
  static final int POP = 0;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Queue<Number> pq = new PriorityQueue<>();

    int N = Integer.parseInt(br.readLine());

    while (N-- > 0) {
      int value = Integer.parseInt(br.readLine());

      if (value == POP) {
        Number popValue = pq.poll();

        if (popValue == null)
          System.out.println(0);
        else {
          System.out.println(popValue.num);
        }

        continue;
      }
      pq.add(new Number(value));
    }

  }
}