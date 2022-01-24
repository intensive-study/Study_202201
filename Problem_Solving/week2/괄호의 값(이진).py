import sys
input = sys.stdin.readline

brackets = input()
stack = []
answer = 0

for x in brackets:
	if x == '(' or x == '[': #open
		stack.append(x)
	elif x == ')':
		temp = 0
		if not stack:
			print(0)
			sys.exit(0)
		while stack:
			top = stack.pop()
			if top == '(':
				if temp == 0:
					stack.append(2)
				else:
					stack.append(temp * 2)
				break
			elif top == '[':
				print(0)
				sys.exit(0)
			else:
				temp += top

	elif x == ']':
		temp = 0
		if not stack:
			print(0)
			sys.exit(0)
		while stack:
			top = stack.pop()
			if top == '[':
				if temp == 0:
					stack.append(3)
				else:
					stack.append(temp * 3)
				break
			elif top == '(':
				print(0)
				sys.exit(0)
			else:
				temp += top
	else:
		print(0)
		sys.exit(0)

for x in stack:
	if x =='(' or x == '[':
		print(0)
		sys.exit(0)
	else:
		answer += x
print(answer)		