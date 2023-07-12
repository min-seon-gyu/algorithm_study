n=int(input())
array=list(list(map(int,input().split())) for i in range(n))

for i in range(1, n):
    for j in range(0, i+1):
        if(j==0):
            array[i][j]+=array[i-1][j]
        elif(j==i):
            array[i][j]+=array[i-1][j-1]
        else:
            array[i][j]+=max(array[i-1][j], array[i-1][j-1])

print(max(array[n-1]))
