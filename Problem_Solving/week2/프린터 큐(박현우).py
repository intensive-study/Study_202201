import sys
from collections import deque

def input():
    return sys.stdin.readline().rstrip()

def function(queue, M):
    ans = 1
    while True:
        MAX = 0
        for i in range(len(queue)):
            MAX = max(MAX, queue[i][1])
        if queue[0][1] == MAX:
            if queue[0][0] == M:
                return ans
            else: 
                queue.popleft()
                ans += 1
        else:
            queue.rotate(-1)

T = int(input())
for i in range(T):
    N, M = map(int, input().split())
    queue = deque()
    arr = list(map(int, input().split()))
    for j in range(N):
        queue.append((j, arr[j]))
    print(function(queue, M))