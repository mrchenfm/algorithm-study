package com.cfm.algorithm.day2;

import java.nio.charset.Charset;
import java.util.Stack;

/**
 * @version v1.0
 * @ProjectName: algorithm-study
 * @ClassName: Calculator
 * @Description: 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 *
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 * @Author: fangming_chen
 * @Date: 2021/03/16 22:28
 */
public class Calculator {

	public static void main(String[] args) {
		calculator("12-3*4");
	}

	public static  int calculator(String str){
		str = str.replace(" ","");
		int res = 0;
		char[] chars = str.toCharArray();
		Stack<Integer> stack = new Stack<Integer>();
		char op = '+';
		int n =0;
		for(int i=0; i<chars.length; i++){
			char c = chars[i];
			if(Character.isDigit(c)){
				n = n*10 + (c-'0');
			}
			if(!Character.isDigit(c) || i == str.length() -1){
				switch (op){
					case '+' : stack.push(n);
						break;
					case '-' : stack.push(-n);
					    break;
					case '*' : stack.push(stack.pop()*n);
						break;
					case '/' : stack.push(stack.pop()/n);
						break;

				}
				op = c;
				n = 0;
			}
		}
		while (!stack.empty()){
			res += stack.pop();
		}
		return res;
	}

}
