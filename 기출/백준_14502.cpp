#include <bits/stdc++.h>
using namespace std;
int arr[8][8];
bool vis[8][8];
int n, m;
int yarr[4] = {0,0,1,-1};
int xarr[4] = {1,-1,0,0};
vector<pair<int,int>> vec;
int bfs()
{
    memset(vis,0,sizeof(vis));
    int now1[n][m];
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<m;j++)
        {
            now1[i][j] = arr[i][j];
        }
    }
    queue<pair<int,int>> q1;
    for(auto now : vec)
        q1.push(now);
    
    while(!q1.empty())
    {
        auto now = q1.front();
        q1.pop();
        for(int i=0;i<4;i++)
        {
            int y1 = now.first + yarr[i];
            int x1 = now.second + xarr[i];
            if(y1<0 || y1>=n || x1<0 || x1>=m)
                continue;
            if(vis[y1][x1])
                continue;
            if(now1[y1][x1] != 0)
                continue;
            vis[y1][x1] = 1;
            now1[y1][x1] = 2;
            q1.push({y1,x1});
        }
    }
    int count = 0;
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<m;j++)
        {
            if(now1[i][j] == 0)
                count++;
        }
    }
    return count;
}
int solve(int count)
{
    if (count == 3)
        return bfs();
        
    int cur = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (arr[i][j] == 0)
            {
                arr[i][j] = 1;
                cur = max(solve(count+1),cur);
                arr[i][j] = 0;
            }
        }
    }
    return cur;
}
int main()
{
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> arr[i][j];
            if(arr[i][j] == 2)
                vec.push_back({i,j});
        }
    }
    cout << solve(0);
}
