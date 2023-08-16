#include <string>
#include <vector>
using namespace std;
long long solve(vector<int> &queue1, vector<int> &queue2, long long sum1, long long sum2)
{
    int target = (sum1 + sum2)/2;
    int n = queue1.size();
    int st1 = 0;
    int st2 = 0;
    int cnt = 0;
    while(st1<queue1.size() || st2 < queue2.size())
    {
        if(cnt == n*4)
            break;
        if(sum1 > sum2)
        {
            sum1-=queue1[st1];
            sum2+=queue1[st1];
            queue2.push_back(queue1[st1]);
            st1++;
            cnt++;
        }
        else if(sum1<sum2)
        {
            sum2-=queue2[st2];
            sum1+=queue2[st2];
            queue1.push_back(queue2[st2]);
            st2++;
            cnt++;
        }
        else
            break;
    }
    if(sum1 != sum2)
        return -1;
    return cnt;
}
int solution(vector<int> queue1, vector<int> queue2) {
    int answer = -2;
    long long sum1 = 0;
    long long sum2 = 0;
    for(auto now : queue1)
        sum1 += now;
    for(auto now : queue2)
        sum2 += now;
    if((sum1+sum2)%2 == 1)
        return -1;
    if(sum1 > sum2)
        answer = solve(queue1,queue2,sum1,sum2);
    else
        answer = solve(queue2,queue1,sum2,sum1);
    return answer;
}
