package com.csh.cc.a;

/**
 * 
 * @author Administrator
 * @date 2019年4月16日
 * @package com.csh.cc.t01 java并发测试
 * synchronized (this)和synchronized方法是相同的都是锁当前对象
 */
public class Test_01 {

	private int count = 0;
	private Object o = new Object();

	public void test01() {
		synchronized (o) {
			System.out.println(" test 01 ");
		}
	}

	public void test02() {
		synchronized (this) {
			System.out.println(" test 02 ");
		}
	}

	public synchronized void test03() {
		System.out.println(" test 03 ");
	}

	/**
	 * 静态同步方法，锁的是当前类型对象。Test_01.class(类名.class)
	 */
	public static synchronized void test04() {
		
	}
}
