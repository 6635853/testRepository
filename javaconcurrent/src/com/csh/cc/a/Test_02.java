package com.csh.cc.a;

/**
 * 测试线程的原子性
 * 
 * @author Administrator
 * @date 2019年4月16日
 * @package com.csh.cc.t
 */
public class Test_02 implements Runnable {

	private int count = 0;

	@Override
	public /* synchronized */ void run() {
		System.out.println(Thread.currentThread().getName() + " count " + count++);
	}

	public static void main(String[] args) {
		Test_02 t02 = new Test_02();
		for (int i = 0; i < 5; i++) {
			new Thread(t02, "Thread - " + i).start();
		}
	}
}
