package com.crazyloong.cat.leetCode.Sort;

public class ShellSort implements Sorter {
    @Override
    public void sort(int[] a) {
        int h = 1;
        while (h < a.length/3){
            h = h*3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h ; j = j - h) {
                    if(a[j] < a[j-h]) {
                        int tmp = a[j];
                        a[j] = a[j-h];
                        a[j-h] = tmp;
                    }
                }
            }
            h = h/3;
        }
    }
}
