package com.cfm.algorithm.day5;

/**
 * @version v1.0
 * @ProjectName: algorithm-study
 * @ClassName: ArrayEventSum
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/31 21:03
 */
public class ArrayEventSum {

	public static void main(String[] args){
		int sum = getSum(new int[]{1, 4,2,5,3});
		System.out.println(sum);
	}

	public static int getSum(int[] arr){

		int res = 0;
		for(int i = 0;i<arr.length;i++){
			int left = i+1;
			int right = arr.length -i;

			int left_event = (left+1)/2;
			int left_odd = left/2;

			int right_event = right/2;
			int right_odd = (right+1)/2;

			res += (left_event*right_odd+left_odd*right_event)*arr[i];
		}

		return res;
	}

}
