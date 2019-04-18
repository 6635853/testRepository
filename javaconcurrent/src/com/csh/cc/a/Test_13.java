package com.csh.cc.a;

/**
 * 在同步代码块中不能使用常量作为锁定的对象
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.t
 */
public class Test_13 {

	String s1 = "hello";
	String s2 = "hello";// new String("hello); 或者 helloworld
	Integer i1 = new Integer(12);
	Integer i2 = new Integer(12);

	void m1() {
		synchronized (i1) {
			System.out.println(" m1 ");
			while (true) {

			}
		}
	}

	void m2() {
		synchronized (i2) {
			System.out.println(" m2 ");
			while (true) {

			}
		}
	}

	public static void main(String[] args) {
		Test_13 t13 = new Test_13();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t13.m1();
			}
		}).start();

		new Thread(() -> {
			t13.m2();
		}).start();
	}
}
