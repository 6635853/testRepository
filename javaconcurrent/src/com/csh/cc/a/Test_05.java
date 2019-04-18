package com.csh.cc.a;

import java.util.concurrent.TimeUnit;

/**
 * 可重入锁
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.t
 */
public class Test_05 {

	public synchronized void m1() {
		System.out.println(" m1 start !");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m2();
		System.out.println(" m1 end !");
	}

	public synchronized void m2() {
		System.out.println(" m2 start !");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" m2 end !");
	}

	public static void main(String[] args) {
		/*
		 * for (int i = 0; i < 2; i++) { new Thread(() -> { new Test_05().m1();
		 * }).start(); }
		 */
		new Test_05().m1();

	}

}
