package com.csh.cc.g;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 固定容量的线程池 ExecutorService 线程池服务类型 Executors -》 Executor的工具类，可以更简单的创建若干种线程池
 * shutdown() -》 优雅关闭，并不是强行关闭回收线程池中的资源，而是不再处理新的任务，将已经接收的任务处理完毕后在关闭
 * 
 * @author Administrator
 * @date 2019年4月18日
 * @package com.csh.cc.g
 */
public class Test_02 {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);// 创建一个线程大小为5的线程池
		for (int i = 0; i < 6; i++) {
			service.execute(() -> {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "   ====   ");
			});
		}
		System.out.println(service);
		service.shutdown();// 停止服务
		System.out.println(service.isTerminated());// 是否已经结束
		System.out.println(service.isShutdown());// 是否已经停止
		System.out.println(service);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(" ************************************** ");
		System.out.println(service.isTerminated());// 是否已经结束
		System.out.println(service.isShutdown());// 是否已经停止
		System.out.println(service);
	}
}
