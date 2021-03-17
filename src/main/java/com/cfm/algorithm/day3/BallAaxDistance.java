package com.cfm.algorithm.day3;

/**
 * @version v1.0
 * @ProjectName: algorithm-study
 * @ClassName: BallAaxDistance
 * @Description: 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子的位置在 position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。
 *
 * 已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。
 *
 * 给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。

 * @Author: fangming_chen
 * @Date: 2021/03/17 22:19
 */
public class BallAaxDistance {

	public static void main(String[] args) {

	}

	/**
	 * 有最大和最小值（采用二分法）
	 * 在假想情况下这个最小值的最大值就是最大值减最小值的平均值
	 * 最小也就是1，这样就可以给他一个预测值1
	 * @param arr
	 * @param m
	 * @return
	 */
	public int getMinMaxValue(int[] arr,int m){
		int mdmv = (arr[arr.length-1]-arr[0])/m;

		int guessValue = 1;

		int actualValue = 1;

		while(guessValue <= mdmv){
			int mid = guessValue + (mdmv - guessValue)/2;

			if(checkIsMid(arr,mid,m)){
				guessValue = mid + 1;
				actualValue = mid;
			}else{
				mdmv = mid - 1;
			}
		}
		return actualValue;
	}

	/**
	 * 判断这个中间值是否合理
	 * @param arr
	 * @param mid
	 * @param m
	 * @return
	 */
	private boolean checkIsMid(int[] arr, int mid, int m) {
		int count = 1;
		int i = 0;
		for(int j = 1;j<arr.length;j++){
			//如果这两个数相减大于等于中间值那么说明合理（也就是这两个数中间可以包含这个数）并且还需要有与球相同数的匹配次数
			if(arr[j] - arr[i] >= mid){
				i = j;
				count++;
				if(count >= m){
					return true;
				}
			}
		}
		return false;
	}

}
