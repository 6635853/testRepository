package com.csh.cc.c;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.c
 */
public class Test_04 {

	public static void main(String[] args) {
		TestReentrantLock testR = new TestReentrantLock();
		// SyncTest testR = new SyncTest();
		// 启动两个线程对象
		Thread t1 = new Thread(testR);
		Thread t2 = new Thread(testR);
		t1.start();
		t2.start();
	}
}

class TestReentrantLock extends Thread {

	//构造参数为true表示公平锁
	private static ReentrantLock lock = new ReentrantLock(true);

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			lock.lock();
			System.out.println(Thread.currentThread().getName());
			lock.unlock();
		}
	}
}

class SyncTest extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			synchronized (this) {
				System.out.println(Thread.currentThread().getName());
			}
		}
	}

}
