package com.csh.cc.a;

/**
 * 测试同步方法和非同步方法是否可以同时执行
 * 
 * @author Administrator
 * @date 2019年4月16日
 * @package com.csh.cc.t
 */
public class Test_03 {

	Object o = new Object();

	public synchronized void m1() {
		System.out.println(" m1 Method Start !");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" m1 Method End !");
	}

	public void m2() {
		System.out.println(" m2 Method Start !");
		try {
			Thread.sleep(1500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" m2 Method End !");
	}

	public static class MyTask implements Runnable {
		private int i;
		Test_03 t;

		public MyTask(int i, Test_03 t03) {
			this.i = i;
			this.t = t03;
		}

		@Override
		public void run() {
			if (i == 0) {
				t.m1();
			} else {
				t.m2();
			}
		}

	}

	public static void main(String[] args) {
		Test_03 t03 = new Test_03();
		new Thread(new MyTask(0, t03)).start();
		new Thread(new MyTask(1, t03)).start();
	}
}
