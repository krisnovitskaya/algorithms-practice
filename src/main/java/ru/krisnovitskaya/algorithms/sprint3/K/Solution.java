package ru.krisnovitskaya.algorithms.sprint3.K;

import java.util.Arrays;

public class Solution {
	public static int[] merge(int[] arr, int left, int mid, int right) {
		int[] result = new int[right - left];
		int l = left;
		int r = mid;
		int k = 0;
		while (l < mid && r < right){
			if(arr[l] < arr[r]){
				result[k] = arr[l];
				l++;
			}else {
				result[k] = arr[r];
				r++;
			}
			k++;
		}
		while (l < mid){
			result[k] = arr[l];
			l++;
			k++;
		}
		while (r < right){
			result[k] = arr[r];
			r++;
			k++;
		}
		return result;
	}

	public static void merge_sort(int[] arr, int left, int right) {
		if (right - left <= 1) {  // базовый случай рекурсии
			return;
		}

		int mid = left + (right - left)/2;
		merge_sort(arr, left, mid);
		merge_sort(arr, mid, right);
		int[] merge = merge(arr, left, mid, right);
		int i = left;
		int m = 0;
		while (i < right){
			arr[i] = merge[m];
			i++;
			m++;
		}
	}

	public static void main(String[] args) {
		int[] a = {1, 4, 9, 2, 10, 11};
		int[] b = merge(a, 0, 3, 6);
		int[] expected = {1, 2, 4, 9, 10, 11};
		assert Arrays.equals(b, expected);
		int[] c = {1, 4, 2, 10, 1, 2};
		merge_sort(c, 0, 6);
		int[] expected2 = {1, 1, 2, 2, 4, 10};
		assert Arrays.equals(c, expected2);

		int[] aa = {4, 5, 3, 0, 1, 2};
		merge_sort(aa, 0, 4);
		printArr(aa);
	}

	static void printArr(int[] arr){
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}