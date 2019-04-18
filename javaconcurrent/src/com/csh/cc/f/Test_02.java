package com.csh.cc.f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * CopyOnWriteList 写时效率非常低，使用空间换时间的方式实现并发 删除尾巴数据时效率也高，删除其他的数据时效率低，读取数据时效率高使用get
 * 
 * @author Administrator
 * @date 2019年4月18日
 * @package com.csh.cc.f
 */
public class Test_02 {

	public static void main(String[] args) {
		 final List<String> list = new ArrayList<String>();//线程不安全，会丢失数据
		// final List<String> list=new CopyOnWriteArrayList<>();//效率低
//		final List<String> list = new Vector<String>();//线程安全，效率相对低些
		final Random random = new Random();
		// 声明数组
		Thread[] t = new Thread[100];
		final CountDownLatch latch = new CountDownLatch(t.length);
		long start = System.currentTimeMillis();
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					list.add("value:" + random.nextInt(1000));
				}
				latch.countDown();
			});
		}
		for (Thread thread : t) {
			thread.start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("时间：" + (end - start) / 1000 + " 大小：" + list.size());
	}
}
