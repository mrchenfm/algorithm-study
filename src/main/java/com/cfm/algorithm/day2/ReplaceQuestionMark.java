package com.cfm.algorithm.day2;

/**
 * @version v1.0
 * @ProjectName: algorithm-study
 * @ClassName: ReplaceQuestionMark
 * @Description: 给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
 *
 * 注意：你 不能 修改非 '?' 字符。
 *
 * 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
 *
 * 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
 * @Author: fangming_chen
 * @Date: 2021/03/16 22:22
 */
public class ReplaceQuestionMark {

	public static void main(String[] args) {
		System.out.println(replaceQuestionMark("?sd"));
	}

	public static String replaceQuestionMark(String s){
		char[] chars = s.toCharArray();

		for(int i=0 ;i<chars.length;i++){
			if(chars[i] == '?'){
				char ahead = i == 0 ? ' ' : chars[i-1];
				char behind = i == chars.length-1 ? ' ' : chars[i+1];

				char temp = 'a';

				while(temp == ahead || temp == behind){
					temp++;
				}
				chars[i] = temp;
			}
		}
		return new String(chars);
	}
}
