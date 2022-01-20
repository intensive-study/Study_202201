import sys
input = sys.stdin.readline

N, K = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(K)]
chess_pieces = [[[] for _ in range(N)] for _ in range(N)]
chess = [0 for _ in range(K)]

dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]

for chess_num in range(K):
    row, col, dir = map(lambda x: int(x) - 1, input().split())
    chess_pieces[row][col].append(chess_num)
    chess[chess_num] = [row, col, dir]
    

# for i in range(K):
#     print(chess_map[i])

def move_chess(chess_num):
    row, col, dir = chess[chess_num]
    if chess_num != chess_pieces[row][col][0]:
        return 0    
    nrow, ncol = row + dx[dir], col + dy[dir]
    if not(0 <= nrow < N) or not(0 <= ncol < N) or board[nrow][ncol] == 2: # 범위밖 or 파란색
        if dir % 2 == 0:
            ndir = dir + 1
        else:
            ndir = dir - 1
        nrow, ncol = row + dx[ndir], col + dy[ndir]
        if not(0 <= nrow < N) or not(0 <= ncol < N) or board[nrow][ncol] == 2:
            return 0
        # else:
        #     chess_pieces[nrow][ncol].extend(chess_num)
        #     chess_pieces[row][col] = []
            
            
            
    
    else: # 범위 안 & 파란색 아닐때
        if board[nrow][ncol] == 0:
            chess_pieces[nrow][ncol].extend(chess_num)
            chess_pieces[row][col] = []
            chess[chess_num] = [nrow, ncol, dir]
            
        elif board[nrow][ncol] == 1:
            chess_pieces[nrow][ncol] = chess_pieces[row][col][::-1]
            for i in chess_pieces[nrow][ncol]:
                chess[i] = [nrow, ncol, dir]  
    
    

turn_cnt = 0

while True:
    if turn_cnt > 1000:
        print(-1)
        break
    for chess_num in range(K):
        flag = move_chess(chess_num)
        if flag:
            print(turn_cnt)
            exit(-1)
    turn_cnt += 1