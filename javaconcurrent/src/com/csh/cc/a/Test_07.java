package com.csh.cc.a;

import java.util.concurrent.TimeUnit;

/**
 * 同步方法中发生异常的时候自动释放锁资源
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.t
 */
public class Test_07 {

	int i = 0;

	public synchronized void m() {
		System.out.println(Thread.currentThread().getName() + " Start ");
		while (true) {
			i++;
			System.out.println(Thread.currentThread().getName() + " print i " + i);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (i == 5) {
				i = 1 / 0;
			}
		}
	}

	public static void main(String[] args) {
		Test_07 t07 = new Test_07();
		new Thread(() -> {
			t07.m();
		}, "t1").start();
		new Thread(() -> {
			t07.m();
		}, "t2").start();
	}
}
