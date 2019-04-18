package com.csh.cc.a;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtoMicxxx主要是源自操作类型其中的每个方法都是原子操作，可以保证线程安全
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.t
 */
public class Test_10 {

	AtomicInteger ato = new AtomicInteger(0);

	void m() {
		for (int i = 0; i < 10000; i++) {
			ato.incrementAndGet();// 相当于++i先自加在获取
			// ato.getAndIncrement();//相当于i++现获取在自加
		}
	}

	public static void main(String[] args) {
		// 创建对象
		Test_10 t10 = new Test_10();
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(() -> {
				t10.m();
			}));
		}
		// 启动线程
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(" this result :" + t10.ato.intValue());
	}
}
