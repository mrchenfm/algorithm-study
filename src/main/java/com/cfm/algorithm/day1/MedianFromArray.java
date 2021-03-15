package com.cfm.algorithm.day1;

/**
 * @version v1.0
 * @ProjectName: algorithm-study
 * @ClassName: MedianFromArray
 * @Description: 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数
 * @Author: fangming_chen
 * @Date: 2021/03/15 23:16
 */
public class MedianFromArray {

	public static void main(String[] args) {

	}

	public static double findMedianSortedArrays(int[] n1,int[] n2){
		int n1Len = n1.length;
		int n2Len = n2.length;
		int left = (n1Len+n2Len+1)/2;
		int right = (n1Len+n2Len+2)/2;
		return (getMedian(n1,0,n1Len-1,n2,0,n2Len-1,left)+getMedian(n1,0,n1Len-1,n2,0,n2Len-1,right))*0.5;
	}

	private static int getMedian(int[] n1, int start1, int end1, int[] n2, int start2, int end2, int k) {
		int len1 = end1 - start1 + 1;
		int len2 = end2 - start2 + 1;

		if(len1 > len2){
			return getMedian(n2,start2,end2,n1,start1,end1,k);
		}

		if(len1 == 0){
			return n2[k+start2-1];
		}

		int i = start1 + Math.min(len1,k/2) - 1;
		int j = start2 + Math.min(len2,k/2) - 1;

		if(n1[i] > n2[j]){
			return getMedian(n1,start1,end1,n2,j+1,end2,k-(j-start2+1));
		}else {
			return getMedian(n1,i+1,end1,n1,j+1,end1,k-(i-start2+1));
		}
	}

}
