package com.cfm.algorithm.day2;

/**
 * @version v1.0
 * @ProjectName: algorithm-study
 * @ClassName: MainElements
 * @Description: 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
 * @Author: fangming_chen
 * @Date: 2021/03/16 22:48
 */
public class MainElements {

	public static void main(String[] args) {
		int[] arr = {11,1,1,1,1,2,3,4,5,1,2,3,2,2,2,2,2,2,2,2,2,2,2,2,2};
		System.out.println(getMainElement(arr));
	}

	public static int getMainElement(int[] arr){

		if(arr == null || arr.length <=0){
			return -1;
		}

		int major = 0;
		int count = 0;

		for(int num : arr){
			if(count == 0){
				major = num;
				count++;
			}else {
				if(major == num){
					count++;
				}else {
					count--;
				}
			}
		}

		if(count == 0){
			return -1;
		}
		int indentify = 0;

		for(int num : arr){
			if(num == major){
				indentify++;
				if(indentify > arr.length/2){
					return major;
				}
			}
		}
		return -1;
	}
}
