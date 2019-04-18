package com.csh.cc.f;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * 同步容器 ConcurrentHashMap和ConcurrentSkipListMap
 * 
 * @author Administrator
 * @date 2019年4月18日
 * @package com.csh.cc.f
 */
public class Test_01 {

	public static void main(String[] args) {
		// final Map<String, String> map = new Hashtable<String, String>();
		 final Map<String, String> map = new ConcurrentHashMap<String, String>();
//		final Map<String, String> map = new ConcurrentSkipListMap<>();
		final Random random = new Random();
		Thread[] t = new Thread[100];
		final CountDownLatch count = new CountDownLatch(t.length);
		long start = System.currentTimeMillis();
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(() -> {
				for (int j = 0; j < 1000000; j++) {
					map.put("key" + random.nextInt(1000000), "value" + random.nextInt(100000));
				}
				count.countDown();
			});
		}
		for (Thread thread : t) {
			thread.start();
		}
		try {
			count.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("共消耗：" + (end - start) / 1000 + " s!");
	}
}
