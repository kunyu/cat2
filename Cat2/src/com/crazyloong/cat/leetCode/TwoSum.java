package com.crazyloong.cat.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 *给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 *
 */
public class TwoSum {

    /**
     * 自己的答案
     *
     */
    public int[] twoSum(int[] nums, int target) {
        int before ;
        int after ;
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            before = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                after = nums[j];
                if (before + after == target){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }

        return result;
    }

    /**
     *
     * 官方答案一
     *
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     *
     * 官方答案二
     *
     */
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        TwoSum t = new TwoSum();
        int[] nums = new int[]{2,7,11,15};
        int[] result = new int[2];
        result =  t.twoSum3(nums,9);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
