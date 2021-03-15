package com.cfm.algorithm.day1;

/**
 * @version v1.0
 * @ProjectName: algorithm-study
 * @ClassName: Test
 * @Description: 转置矩阵
 * @Author: fangming_chen
 * @Date: 2021/03/15 21:41
 */
public class MatrixTransport {

	public static void main(String[] args) {
		int[][] arr = {{1,2,3},{4,5,6},{7,8,8}};
		int[][] transport = transport(arr);
		for(int i=0 ;i<transport.length;i++){
			for(int j =0 ;j<transport[0].length;j++){
				System.out.println(transport[i][j]);
			}
		}
	}

	public static int[][] transport(int[][] matrix){
		int m = matrix.length;
		int n = matrix[0].length;

		int[][] result = new int[n][m];
		for(int i=0 ;i<m;i++){
			for(int j =0 ;j<n;j++){
				result[j][i] = matrix[i][j];
			}
		}
		return result;
	}
}
