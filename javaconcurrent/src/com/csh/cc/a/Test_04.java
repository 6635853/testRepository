package com.csh.cc.a;

import java.util.concurrent.TimeUnit;

/**
 * 同步方法调用之间的原子性问题
 * 
 * @author Administrator
 * @date 2019年4月16日
 * @package com.csh.cc.t
 */
public class Test_04 {

	private double d = 0.0;

	public synchronized void m1(double e) {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		this.d = e;
		System.out.println("this.d" + this.d);
	}

	public double m2() {
		return this.d;
	}

	public static void main(String[] args) {

		final Test_04 t04 = new Test_04();
		new Thread(() -> {
			System.out.println("in");
			t04.m1(100);
			System.out.println("out");
		}).start();
		System.out.println("---" + t04.m2());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("====" + t04.m2());
	}
}
