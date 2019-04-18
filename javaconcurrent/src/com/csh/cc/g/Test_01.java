package com.csh.cc.g;

import java.util.concurrent.Executor;

/**
 * 线程池
 * 
 * @author Administrator
 * @date 2019年4月18日
 * @package com.csh.cc
 */
public class Test_01 implements Executor {

	public static void main(String[] args) {
		new Test_01().execute(() -> {
			System.out.println(Thread.currentThread().getName() + " = test executor");
		});
	}

	@Override
	public void execute(Runnable command) {
		new Thread(command).start();
	}
}
