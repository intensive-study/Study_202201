def check_intersection(circle1, circle2):
    start1, end1 = circle1
    start2, end2 = circle2
    if start1 <= start2 <= end1:
        if end1 <= end2:
            return False
        else: # end2 < end1
            return True
    return True

def solve():
    N = int(input())
    circles = []
    for _ in range(N):
        x, r = map(int, input().split())
        circles.append([x-r, x+r])
    circles.sort()
    flag = True
    for i in range(N-1):
        if check_intersection(circles[i], circles[i+1]):
            continue
        else:
            flag = False
            print('NO')
            return
    if flag:
        print('YES')


if __name__=="__main__":
    solve()