package com.csh.cc.c;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可打断
 * 阻塞状态：普通阻塞，等待队列，琐池队列
 * 普通阻塞：可被打断，调用thread.interrupt()方法
 * 等待队列：wait()方法，也是一种阻塞方法，调用notify方法可打断
 * 琐池队列：无法获取锁的标记，获取锁标记的时候，如果等待则无法打断
 * 使用ReentrantLock的lock方法不可被打断
 * 使用ReentrantLock的lockInterruptibly方法可被打断
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.c
 */
public class Test_03 {

	Lock lock = new ReentrantLock();

	public void m1() {
		try {
			lock.lock();
			for (int i = 0; i < 5; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("m1 method");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void m2() {
		try {
			lock.lockInterruptibly();// 可被打断的，是阻塞等待状态的锁，
			System.out.println(" m2 method");
		} catch (Exception e) {
			System.err.println("被打断了");
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		Test_03 t03 = new Test_03();
		// 首先启动一个线程
		new Thread(() -> {
			t03.m1();
		}).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 再启动第二个线程
		Thread t2 = new Thread(() -> {
			t03.m2();
		});
		t2.start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.interrupt();//打断休眠的线程
	}
}
