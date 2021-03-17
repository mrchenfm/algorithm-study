package com.cfm.algorithm.day3;

/**
 * @version v1.0
 * @ProjectName: algorithm-study
 * @ClassName: ArraysWiner
 * @Description: 给你一个由 不同 整数组成的整数数组 arr 和一个整数 k 。
 *
 * 每回合游戏都在数组的前两个元素（即 arr[0] 和 arr[1] ）之间进行。比较 arr[0] 与 arr[1] 的大小，较大的整数将会取得这一回合的胜利并保留在位置 0 ，较小的整数移至数组的末尾。当一个整数赢得 k 个连续回合时，游戏结束，该整数就是比赛的 赢家
 */
public class ArraysWinner {

	public static void main(String[] args) {
		getWinner(new int[]{2,1,3,5,4,6,7},2);
	}

	public static int getWinner(int[] arr,int k){
		int left = 0;
		int right = 0;

		while(right < arr.length){
			//如果left == 0，并且已经k次比较，那么此时right应该就是k+1，说明left这个数有k个比他小所以可以结束
			//如果left !=0 ,然后循环了k次后还是left位置的数大，那么就可以结束并返回这个数
			if((left == 0 && right - left == k+1) || (left != 0 && right- left == k)){
				break;
			}
			if(arr[left] <arr[right]){
				left = right;
			}
			right++;
		}

		return arr[left];
	}

}
