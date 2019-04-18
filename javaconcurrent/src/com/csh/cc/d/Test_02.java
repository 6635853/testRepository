package com.csh.cc.d;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test_02<E> {

	private final LinkedList<E> list = new LinkedList<E>();
	private final int MAX = 10;
	private int count;

	private Lock lock = new ReentrantLock();

	private Condition consumer = lock.newCondition();// 消费者
	private Condition product = lock.newCondition();// 生产者

	// 生产者
	public void put(E e) {
		try {
			lock.lock();
			while (list.size() == MAX) {
				product.await();// 生产者进入等待状态
			}
			list.add(e);
			count++;
			consumer.signalAll();// 唤醒消费者线程
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	// 消费者
	public E get() {
		E e = null;
		try {
			lock.lock();
			while (list.size() == 0) {
				// 消费者等待
				consumer.await();
			}
			e = list.removeFirst();
			count--;
			product.signalAll();// 唤醒生产者
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			lock.unlock();
		}
		return e;
	}

	public static void main(String[] args) {
		final Test_02<String> t02 = new Test_02<String>();
		// 启动10个消费者
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 5; j++) {
					System.out.println(" consumer :" + t02.get());
				}
			}, "consumer" + i).start();
		}
		// 启动两个生产者
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				for (int j = 0; j < 25; j++) {
					t02.put(Thread.currentThread().getName() + " = " + j);
				}
			}, "product " + i).start();
		}

	}
}
