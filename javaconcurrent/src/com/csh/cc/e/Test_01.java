package com.csh.cc.e;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 本地线程变量，相当于一个map。key-》Thread.getCurrentThread() value-》线程需要保存的变量
 * ThreadLocal.set(value)->map.put(Thread.getCurrentThread(),value);
 * ThreadLocal.get()->map.get(Thread.getCurrentThread());
 * 需要注意的是在每个线程结束后一定要注意资源回收问题，将当前线程的变量删除
 * @author Administrator
 * @date 2019年4月18日
 * @package com.csh.cc.e
 */
public class Test_01 {

	volatile static String name = "zhangsan";
	static ThreadLocal<String> tl = new ThreadLocal<String>();

	public static void main(String[] args) {
		// 启动一个线程
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println(" name: " + name);//最后输出是lisi
				System.out.println(" tl get :" + tl.get());//最后输出 是null，原因是ThreadLocal本地线程变量，只保存所处在的某个线程的变量信息，而此处是在第二个线程
				//中set了值，不是同一个ThreadLocal变量，所以并不能取到值
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}).start();

		// 启动第二个线程
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				name = "lisi";
				tl.set("wangwu");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
