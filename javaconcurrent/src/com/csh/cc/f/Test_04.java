package com.csh.cc.f;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * BlockingQueue 阻塞队列
 * put自动阻塞，在队列容量满时自动阻塞
 * take自动阻塞，在队列容量为0时自动阻塞
 * 
 * @author Administrator
 * @date 2019年4月18日
 * @package com.csh.cc.f
 */
public class Test_04 {

	public static void main(String[] args) {
		final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
		final Random random = new Random();
		new Thread(() -> {
			while (true) {
				try {
					queue.put("add Num" + random.nextInt(1000));
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "product").start();
		for (int i = 0; i < 3; i++) {
			new Thread(() -> {
				try {
					while(true) {
						System.out.println(Thread.currentThread().getName() + " - " + queue.take());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, "consumer" + i).start();
		}
	}
}
