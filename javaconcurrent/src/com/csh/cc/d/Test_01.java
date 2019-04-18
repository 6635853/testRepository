package com.csh.cc.d;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者模式保证线程安全
 * 
 * @author Administrator
 * @date 2019年4月17日
 * @package com.csh.cc.d
 */
public class Test_01<T> {

	private final LinkedList<T> list = new LinkedList<>();
	private final int MAX = 10;
	private int count = 0;

	public synchronized int getCount() {
		return count;
	}

	// 添加
	public synchronized void put(T t) {
		while (this.list.size() == MAX) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		list.add(t);
		count++;
		this.notifyAll();
	}

	public synchronized T get() {
		T t = null;
		while (list.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = list.removeFirst();// 移除一个
		count--;
		this.notifyAll();
		return t;
	}

	public static void main(String[] args) {
		final Test_01<String> t01 = new Test_01<>();
		// 启动10个消费者
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 3; j++) {
					System.out.println("consumer start :" + t01.get());
				}
			}, "consumer" + i).start();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 启动两个生产者
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				for (int j = 0; j < 25; j++) {
					t01.put(" add Num " + j);
				}
			}, "product" + i).start();
		}

	}
}
