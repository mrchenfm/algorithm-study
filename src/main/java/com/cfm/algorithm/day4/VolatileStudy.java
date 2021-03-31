package com.cfm.algorithm.day4;

/**
 * @version v1.0
 * @ProjectName: algorithm-study
 * @ClassName: VolatileStudy
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2021/03/18 22:31
 */
public class VolatileStudy {

	public static boolean flag = false;
	public static void main(String[] args) throws InterruptedException {

		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				if (flag) {
					System.out.println("============");
				}
			}
		});


		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				flag = true;
			}
		});

		thread1.start();

		Thread.yield();

		thread2.start();

		thread1.join();

		thread2.join();


	}
}
