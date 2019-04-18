package com.csh.cc.f;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue 有界队列
 * 
 * @author Administrator
 * @date 2019年4月18日
 * @package com.csh.cc.f
 */
public class Test_05 {

	public static void main(String[] args) {
		final BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
		for (int i = 0; i < 5; i++) {
			// add方法 在添加是如果容量不足，会报出一个异常Queue Full队列满
			// System.out.println(queue.add("add num:"+i));
			// ************************************************************************
			// put方法 容量不足时，并不会报错，只会阻塞在当时
			// try {
			// TimeUnit.SECONDS.sleep(1);
			// queue.put("put" + i);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			// System.out.println("put method:" + i);
			// ************************************************************************
			// offer方法 队列容量满时会抛弃后边的数
			// System.out.println("offer method:" + queue.offer("offer" + i));//一个参数
			try {
				// 三参数 offer 方法（offer(value,times,timeunit)），容量不足的时候，阻塞 times 时长（单
				// 位为 timeunit），如果在阻塞时长内，有容量空闲，新增数据返回 true。如果阻塞时长范围
				// 内，无容量空闲，放弃新增数据，返回 false
				System.out.println("offer method:" + queue.offer("offer", 2, TimeUnit.SECONDS));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(queue);
	}
}
