package com.csh.cc.f;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * {@link TransferQueue}
 * 
 * @author Administrator
 * @date 2019年4月18日
 * @package com.csh.cc.f
 */
public class Test_07 {

	TransferQueue<String> queue = new LinkedTransferQueue<>();

	public static void main(String[] args) {
		final Test_07 t = new Test_07();

		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { try {
		 * System.out.println(Thread.currentThread().getName() + " thread begin " );
		 * System.out.println(Thread.currentThread().getName() + " - " +
		 * t.queue.take()); } catch (InterruptedException e) { e.printStackTrace(); } }
		 * }, "output thread").start();
		 * 
		 * try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 * 
		 * try { t.queue.transfer("test string"); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					t.queue.transfer("test string");
					// t.queue.add("test string");
					System.out.println("add ok");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + " thread begin ");
					System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "output thread").start();

	}
}
