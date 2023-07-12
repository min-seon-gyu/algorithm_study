t=int(input())

def solve(dp,n,coin_list,goal):
    dp[0]=1
    for i in range(0,n):
        for j in range(coin_list[i],goal+1):
            dp[j]=dp[j]+dp[j-coin_list[i]]
    print(dp[goal])
        
for i in range(t):
    n=int(input())
    dp=[0 for _ in range(10001)]
    coin_list=list(map(int,input().split()))
    goal=int(input())
    solve(dp,n,coin_list,goal)
    
