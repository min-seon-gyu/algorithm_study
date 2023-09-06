#include <bits/stdc++.h>
using namespace std;
vector<pair<int,int>> v1[201];
vector<int> dijkstra(int n, int st)
{
    vector<int> d(201,201*100001);
    d[st] = 0;
    priority_queue<pair<int,int>,vector<pair<int,int>>, greater<pair<int,int>>> pq1;
    pq1.push({d[st], st});
    while(!pq1.empty())
    {
        auto now = pq1.top();
        pq1.pop();
        if(d[now.second] != now.first)
            continue;
        for(auto next : v1[now.second])
        {
            if(d[next.second] <= now.first + next.first)
                continue;
            d[next.second] = now.first+next.first;
            pq1.push({d[next.second], next.second});
        }
    }
    return d;
}
int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    for(auto now : fares)
    {
        v1[now[0]].push_back({now[2], now[1]});
        v1[now[1]].push_back({now[2], now[0]});
    }
    int answer = INT_MAX;

    	vector<int> resulta = dijkstra(n,a);
    	vector<int> resultb = dijkstra(n,b);
    	vector<int> resultc = dijkstra(n,s);
    for(int i=1;i<=n;i++)
        answer = min(answer, resulta[i] + resultb[i] + resultc[i]);
    return answer;
}
