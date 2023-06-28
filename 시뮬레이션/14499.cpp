#include <bits/stdc++.h>
using namespace std;
int n, m, x, y, k;
int arr[4][3];  //주사위
int yarr[4] = {0, 0, -1, 1};
int xarr[4] = {1, -1, 0, 0};
int solve(vector<vector<int>> &map1, int dir)
{
    int y1 = y + yarr[dir];
    int x1 = x + xarr[dir];
    if (y1 < 0 || y1 >= n || x1 < 0 || x1 >= m)
        return -1;
    y = y1;
    x = x1;
    int now[4] = {
        0,
    };
    if (dir == 0)
    {
        now[0] = arr[3][1];
        for (int i = 0; i < 3; i++)
            now[i + 1] = arr[1][i];
        for (int i = 0; i < 3; i++)
            arr[1][i] = now[i];
        arr[3][1] = now[3];
    }
    if (dir == 1)
    {
        now[2] = arr[3][1];
        for (int i = 2; i >= 1; i--)
        {
            now[i - 1] = arr[1][i];
        }
        now[3] = arr[1][0];
        for (int i = 0; i < 3; i++)
            arr[1][i] = now[i];
        arr[3][1] = now[3];
    }
    if (dir == 2)
    {
        now[0] = arr[3][1];
        for (int i = 0; i < 3; i++)
            now[i + 1] = arr[i][1];
        for (int i = 0; i < 4; i++)
            arr[i][1] = now[i];
    }
    if (dir == 3)
    {
        now[3] = arr[0][1];
        for (int i = 2; i >= 0; i--)
            now[i] = arr[i + 1][1];
        for (int i = 0; i < 4; i++)
            arr[i][1] = now[i];
    }

    if (map1[y][x] != 0)
    {
        arr[3][1] = map1[y][x];
        map1[y][x] = 0;
    }
    else
        map1[y][x] = arr[3][1];
    return arr[1][1];
}
int main()
{
    memset(arr, 0, sizeof(arr));
    cin >> n >> m >> y >> x >> k;
    vector<int> v1(m);
    vector<vector<int>> map1(n, v1);
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> map1[i][j];
        }
    }
    for (int i = 0; i < k; i++)
    {
        int ar;
        cin >> ar;
        int now = solve(map1, ar - 1);
        if (now == -1)
            continue;
        cout << now << '\n';
    }
}
