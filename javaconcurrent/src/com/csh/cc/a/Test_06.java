package com.csh.cc.a;

import java.util.concurrent.TimeUnit;

/**
 * 子类同步方法，覆盖父类的同步方法，相当于是锁的重入
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.t
 */
public class Test_06 {

	synchronized void m() {
		System.out.println(" Super Method Start! ");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" Super Method End! ");
	}

	public static void main(String[] args) {
		new SubClass().m();
	}
}

class SubClass extends Test_06 {
	@Override
	synchronized void m() {
		System.out.println(" Sub Method Start! ");
		super.m();
		System.out.println(" Sub Method End! ");
	}
}