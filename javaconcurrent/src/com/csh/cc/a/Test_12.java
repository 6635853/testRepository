package com.csh.cc.a;

import java.util.concurrent.TimeUnit;

/**
 * 锁和对象的变更问题，同步代码被加上锁之后，会有一个临时的锁引用指向锁对象，和真实的引用并无直接关联，在未被释放之前，修改锁对象引用不会影响同步代码的执行
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.t
 */
public class Test_12 {

	Object o = new Object();

	public void m() {
		System.out.println(Thread.currentThread().getName() + " start ");
		synchronized (o) {
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "  =  " + o);
			}
		}
	}

	public static void main(String[] args) {
		Test_12 t12 = new Test_12();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t12.m();
			}
		}, "thread1").start();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				t12.m();
			}
		}, "thread2").start();
		t12.o = new Object();

	}
}
