import math
dp=[i for i in range(100001)]
n=int(input())

for i in range(1,n+1):
    p=int(math.sqrt(i))
    for j in range(1,p+1):
        dp[i]=min(dp[i],dp[i-j*j]+1)
        
print(dp[n])
