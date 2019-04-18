package com.csh.cc.a;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.t
 */
public class Test_14 {

	CountDownLatch count = new CountDownLatch(5);

	public void m1() {
		try {
			count.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" m1 Method ");
	}

	public void m2() {
		for (int i = 0; i < 10; i++) {
			if (count.getCount() != 0) {
				// 说明还有锁
				System.out.println(" 锁的数量还剩 " + count.getCount());
				count.countDown();// 减去锁
			}
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(" m2 Method " + i);
		}
	}

	public static void main(String[] args) {
		Test_14 t14 = new Test_14();
		new Thread(() -> {
			t14.m1();
		}).start();
		new Thread(() -> {
			t14.m2();
		}).start();
	}
}
