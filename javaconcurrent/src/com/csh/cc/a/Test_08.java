package com.csh.cc.a;

import java.util.concurrent.TimeUnit;

/**
 * volatile 可见性 内存可见性
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.t
 */
public class Test_08 {

	volatile boolean b = true;

	void m() {
		System.out.println(" start ");
		while (b) {

		}
		System.out.println(" end ");
	}

	public static void main(String[] args) {
		Test_08 t08 = new Test_08();
		new Thread(() -> {
			t08.m();
		}).start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" == ");
		t08.b = false;
		System.out.println(" == " + t08.b);
	}
}
