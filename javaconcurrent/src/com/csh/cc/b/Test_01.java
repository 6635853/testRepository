package com.csh.cc.b;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * volatile可见性问题
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.b
 */
public class Test_01 {

	public static void main(String[] args) {
		Test_01_Container t01 = new Test_01_Container();
		// 线程1，监视容器中的量是否为5
		new Thread(() -> {
			while (true) {
				// System.out.println(Thread.currentThread().getName());
				if (t01.size() == 5) {
					System.out.println("Size 5，Exit!");
					break;
				}
			}
		}, "ListenerThread").start();

		// 线程2 添加数据
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + " Add Num " + i);
				t01.add(i);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "AddThread").start();
	}
}

/**
 * 容器
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.b
 */
class Test_01_Container {
	/*volatile*/ List<Object> list = new ArrayList<>();

	public void add(Object o) {
		this.list.add(o);
	}

	public int size() {
		return this.list.size();
	}
}
