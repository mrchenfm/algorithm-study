package com.cfm.algorithm.day2;

/**
 * @version v1.0
 * @ProjectName: algorithm-study
 * @ClassName: AmountOfWater
 * @Description: 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1
 * @Author: fangming_chen
 * @Date: 2021/03/16 21:07
 */
public class AmountOfWater {

	public static void main(String[] args) {
		int[] histogram = {0,1,0,2,1,0,1,3,2,1,2,1};
		int amountWater = getAmountWater(histogram);
		System.out.println(amountWater);
	}

	public static int getAmountWater(int[] histogram){

		if(histogram == null || histogram.length < 3){
			return 0;
		}
		int left = 0;
		int right = histogram.length-1;
		int leftMax = histogram[left];
		int rightMax = histogram[right];

		int res = 0;

		while(left < right){
			if(leftMax < rightMax){
				res += leftMax - histogram[left++];
				leftMax = Math.max(leftMax,histogram[left]);
			}else {
				res += rightMax -histogram[right--];
				rightMax =  Math.max(rightMax,histogram[right]);
			}
		}

		return res;
	}
}
