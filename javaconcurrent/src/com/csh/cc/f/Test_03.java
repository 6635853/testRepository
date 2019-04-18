package com.csh.cc.f;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 队列ConcurrentLinkedQueue 先进先出
 * 
 * @author Administrator
 * @date 2019年4月18日
 * @package com.csh.cc.f
 */
public class Test_03 {

	public static void main(String[] args) {
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		for (int i = 0; i < 10; i++) {
			queue.offer(" add Num :" + i);// 添加数据
		}
//		System.out.println(queue);
//		System.out.println(queue.size());

		// peek() -》 查看queue中的第一个数据
//		System.out.println(queue.peek());
//		System.out.println(queue.size());

		// poll() -》 获取queue中的第一个数据，但是会删除第一个数据
		System.out.println(queue.poll());//相当于removeFirst
		System.out.println(queue.size());
	}
}
