package com.csh.cc.b;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用countdownlatch
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.b
 */
public class Test_03 {

	public static void main(String[] args) {
		final Test_03_Container t03 = new Test_03_Container();
		final CountDownLatch count = new CountDownLatch(1);

		// 第一个线程进行监视
		new Thread(() -> {
			if (t03.size() != 5) {
				try {
					count.await();//等待门闩开放
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(" Size 5,Exit! ");
			}
		}).start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(" Add Num " + i);
				t03.add(i);
				if (t03.size() == 5) {
					count.countDown();
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}

class Test_03_Container {
	List<Object> list = new ArrayList<>();

	public void add(Object o) {
		this.list.add(o);
	}

	public int size() {
		return this.list.size();
	}
}
