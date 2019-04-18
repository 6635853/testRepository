package com.csh.cc.c;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 重入锁
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.c
 */
public class Test_01 {

	Lock lock = new ReentrantLock();// 声明一个重入锁对象

	public void m1() {
		try {
			lock.lock();
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("m1 Method " + i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();// 需要手动释放
		}
	}

	public void m2() {
		lock.lock();
		System.out.println("m2 method ");
		lock.unlock();
	}

	public static void main(String[] args) {
		Test_01 t01 = new Test_01();
		new Thread(() -> {
			t01.m1();
		}).start();
		new Thread(() -> {
			t01.m2();
		}).start();
	}
}
