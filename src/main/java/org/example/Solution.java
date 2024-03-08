package org.example;

/**
 * @Author hhmm
 * @date 2024/3/7
 **/

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * f(n)=f(n-1)+f(n-2)
 */
public class Solution {
    public int climbStairs(int n) {
        int res=0,n1=1,n2=2;
        if (n<3){
            return 2;
        }
        for (int i = 3; i <= n; i++) {
            res=n1+n2;
            n1=n2;
            n2=res;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().climbStairs(3));
    }
}
