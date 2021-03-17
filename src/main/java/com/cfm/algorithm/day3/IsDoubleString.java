package com.cfm.algorithm.day3;

/**
 * @version v1.0
 * @ProjectName: algorithm-study
 * @ClassName: IsDoubleString
 * @Description: 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * @Author: fangming_chen
 * @Date: 2021/03/17 22:34
 */
public class IsDoubleString {

	public static void main(String[] args) {

	}

	public boolean isOverride(String s){
		String str =s+s;
		return str.substring(1,str.length()-1).contains(s);
	}

}
