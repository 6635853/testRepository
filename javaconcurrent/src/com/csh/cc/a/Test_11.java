package com.csh.cc.a;

/**
 * 商业开发中尽量做到细粒度解决同步问题，可以提高效率问题，尽量使用同步代码块少使用同步方法
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.t
 */
public class Test_11 {

	public synchronized void m() {
		System.out.println(" 业务逻辑 ");
	}

	public void m2() {
		synchronized (this) {
			System.out.println(" 业务逻辑 ");
		}
	}

}
