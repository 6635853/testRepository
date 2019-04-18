package com.csh.cc.b;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 第二种方式使用wait()和notifyAll()方法实现
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.b
 */
public class Test_02 {

	public static void main(String[] args) {
		final Test_02_Container t02 = new Test_02_Container();
		final Object lock = new Object();
		// 第一个线程
		new Thread(() -> {
			synchronized (lock) {
				if (t02.size() != 5) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(" Size 5 ,Exit!");
				lock.notifyAll();//唤醒其他县城
			}
		}).start();
		// 启动第二个线程
		new Thread(() -> {
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					System.out.println(" Add Num " + i);
					t02.add(i);
					if (t02.size() == 5) {
						lock.notifyAll();// 等于5的时候叫醒领一个线程
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
}

/**
 * 容器
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.b
 */
class Test_02_Container {
	List<Object> list = new ArrayList<>();

	public void add(Object o) {
		this.list.add(o);
	}

	public int size() {
		return this.list.size();
	}
}
