import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 파일명때문에 컴파일 안됨
public class BOJ17780 {
    static final int WHITE = 0;
    static final int RED = 1;
    static final int BLUE = 2;
    static final int GOAL = 4;

    static int N;
    static int K;
    static int[][] chessMap;
    static List<int[]>[][] moving;
    static Piece[] order;

    static int[][] directionInfo = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
    static int[] oppsite = { 1, 0, 3, 2 };

    static class Piece {
        int row;
        int col;
        int dir;
        // 핵심
        int height;

        Piece(int row, int col, int dir, int height) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.height = height;
        }

        void changeDirection() {
            dir = oppsite[dir];
        }

        void setHeight(int h) {
            this.height = h;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        chessMap = new int[N][N];
        order = new Piece[K];
        moving = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                // 0: 흰, 1: 빨, 2: 파
                chessMap[i][j] = Integer.parseInt(st.nextToken());
                moving[i][j] = new ArrayList<>();
            }

        }

        for (int p = 0; p < K; p++) {
            st = new StringTokenizer(br.readLine());
            // row, col, direction(1: 동, 2: 서, 3: 북, 4: 남)
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            order[p] = new Piece(row, col, dir, moving[row][col].size());
            moving[row][col].add(new int[] { p, dir });

        }

        System.out.println(run());

    }

    public static int run() {
        int turn = 1;

        while (turn <= 1000) {
            for (int o = 0; o < K; o++) {
                Piece piece = order[o];
                if (piece.height > 0)
                    continue;

                int newRow = piece.row + directionInfo[piece.dir][0];
                int newCol = piece.col + directionInfo[piece.dir][1];

                List<int[]> pieceList = getChild(piece);

                // 범위 밖 넘어가는 지 조사
                if (newRow < 0 || newRow > N - 1 || newCol < 0 || newCol > N - 1 || chessMap[newRow][newCol] == BLUE) {
                    piece.changeDirection();

                    // 만약 반대도 BLUE거나 범위를 넘어서는 경우 continue

                    newRow = piece.row + directionInfo[piece.dir][0];
                    newCol = piece.col + directionInfo[piece.dir][1];

                    if (newRow < 0 || newRow > N - 1 || newCol < 0 || newCol > N - 1
                            || chessMap[newRow][newCol] == BLUE) {
                        piece.changeDirection();
                        continue;
                    }

                }

                // 흰, 빨 -> 이동
                if (chessMap[newRow][newCol] == RED) {
                    // 레드면 역순 정렬
                    Collections.reverse(pieceList);

                }

                // 현 위치에서 합집합 제거
                moving[piece.row][piece.col].removeAll(pieceList);

                // 이동한 위치에 해당 리스트 추가 및 order 내부에 있는 정보 변경
                changeSubListInfo(pieceList, newRow, newCol, moving[newRow][newCol].size());
                moving[newRow][newCol].addAll(pieceList);

                // 4개 이상이었다니....
                if (moving[newRow][newCol].size() >= GOAL)
                    return turn;

                // for (int row = 0; row < N; row++) {
                // for (int col = 0; col < N; col++) {
                // System.out.print(moving[row][col].size() + " ");
                // }
                // System.out.println();

                // }
                // System.out.println();
            }
            // System.out.println(turn);
            turn++;
        }

        return -1;

    }

    private static void changeSubListInfo(List<int[]> subList, int row, int col, int prevHeight) {
        // p, dir
        for (int cnt = 0; cnt < subList.size(); cnt++) {
            int[] cur = subList.get(cnt);
            Piece pieceToChange = order[cur[0]];

            pieceToChange.row = row;
            pieceToChange.col = col;
            pieceToChange.height = prevHeight + cnt;

        }
    }

    public static List<int[]> getChild(Piece piece) {
        List<int[]> subList = new ArrayList<>();
        subList.add(moving[piece.row][piece.col].get(piece.height));

        // 일단 위에 값이 존재하는지 찾기 (현 위치에서 삭제 및 해당 리스트 반환)
        if (moving[piece.row][piece.col].size() - piece.height > 1) {
            // 위로 같이 옮길 자식이 있는 경우

            for (int height = piece.height + 1; height < moving[piece.row][piece.col].size(); height++) {
                subList.add(moving[piece.row][piece.col].get(height));
            }

        }

        return subList;
    }
}
