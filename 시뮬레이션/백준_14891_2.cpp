#include <bits/stdc++.h>
using namespace std;
const int check[2] = {2,6};
void rotate1(vector<vector<int>> &arr, int u, bool checkpos)
{
    if(checkpos)
    {
        int temp = arr[u][7];
        for(int i=6;i>=0;i--)
        {
            arr[u][i+1] = arr[u][i];
        }
        arr[u][0] = temp;
    }
    else
    {
        int temp = arr[u][0];
        for(int i=1;i<=7;i++)
        {
            arr[u][i-1] = arr[u][i];
        }
        arr[u][7] = temp;
    }
}
void solve( vector<vector<int>> &arr, int u, int v, int d,bool checkpos)
{
    if(v <= -1 || v >= 4)
        return ;
    int d1 = (d+1)/2;       // 0, 1
    bool d2 = d1;
    if(arr[u][check[!d2]] == arr[v][check[d2]])
        return ;    
    
    solve(arr,v,v+d,d,!checkpos);
    rotate1(arr,v,checkpos);
}
int main()
{
    int q;
    vector<int> v1(8,0);
    vector<vector<int>> arr(4,v1);
    for(int i=0;i<4;i++)
    {
        for(int j=0;j<8;j++)
        {
            char a;
            cin>>a;
            arr[i][j] = a-'0';
        }
    }
    cin>>q;
    for(int i=0;i<q;i++)
    {
        int u,v;
        cin>>u>>v;
        bool next = (v+1)/2;    //0,1
        solve(arr,u-1,u-2,-1,!next);
        solve(arr,u-1,u,1,!next);
        rotate1(arr,u-1,next);
    }
    int sum = 0;
    for(int i=0;i<4;i++)
        sum += ((1<<i)*arr[i][0]);
    cout<<sum;
}
