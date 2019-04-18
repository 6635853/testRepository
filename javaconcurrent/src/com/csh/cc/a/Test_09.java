package com.csh.cc.a;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile和synchronized区别 volatile只能保证可见性不能保证原子性 synchronized即可保证可见性又可保证原子性
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.t
 */
public class Test_09 {

	volatile int count = 0;

	synchronized void m() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}

	public  static void main(String[] args) {
		final Test_09 t09 = new Test_09();
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(() -> {
				t09.m();
			}));
		}
		// 启动10个线程
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(" final result is :" + t09.count);
	}
}
