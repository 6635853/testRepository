package com.csh.cc.c;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 尝试锁
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.c
 */
public class Test_02 {

	Lock lock = new ReentrantLock();

	public void m1() {
		try {
			lock.lock();
			for (int i = 0; i < 5; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("m1 method!" + i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void m2() {
		boolean flag = false;
		try {
			// TimeUnit.SECONDS.sleep(6);
			// flag = lock.tryLock();
			flag = lock.tryLock(6, TimeUnit.SECONDS);// 在阻塞等待6秒之后尝试是否能获取到锁
			if (flag) {
				System.out.println(" get Lock ");
			} else {
				System.out.println(" no get lock");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		Test_02 t02 = new Test_02();
		new Thread(() -> {
			t02.m1();
		}).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(() -> {
			t02.m2();
		}).start();
	}
}
