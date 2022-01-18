package com.crazyloong.cat.leetCode;

/**
 * 209. 长度最小的子数组
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，
 * 并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 * 示例:
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 *
 * 进阶:
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 */
public class LeetCode209 {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int s = 15;
        //15
        //[1,2,3,4,5]
        System.out.println(new LeetCode209().minSubArrayLen(s,nums));
    }

    public int minSubArrayLen(int s, int[] nums) {
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                int tmp = 0;
                for (int k = 0; k < i; k++) {
                    tmp += nums[j+k];
                }
                if (tmp >= s) {
                    return i;
                }
            }
        }
        return 0;
    }

}
