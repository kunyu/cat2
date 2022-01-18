package com.crazyloong.cat.leetCode.Sort;

public class InsertSort implements Sorter{
    @Override
    public void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j-1]) {
                    int tmp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = tmp;
                }
            }
        }
    }
}
