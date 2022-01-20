word = input()

if word[0] != 'w':
    answer = False
if word[-1] != 'f':
    answer = False

def check(word):
    cnt = 0
    for i in word:
        if i == 'w':
            cnt += 1
        else:
            break
    if word == ('w'*cnt+'o'*cnt+'l'*cnt+'f'*cnt):
        return True
    return False

start = 0
flag = False

for i in range(len(word)):
    if word[0] != 'w' or word[-1] != 'f':
        print(0)
        break
    if word[i] == 'f' and (i == len(word)-1 or word[i+1] == 'w'):
        if check(word[start:i+1]):
            flag = True
            start = i+1
        else:
            print(0)
            break
            
if flag:
    print(1)