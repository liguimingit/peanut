/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 类Test.java的实现描述：Test
 * 
 * @author Bruce 2012年3月26日 下午5:59:22
 */
public class Test {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        // Future future = service.submit(new TaskCallable());
        Future future = service.submit(new TaskRunnble());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("done.");
    }

    static class TaskCallable implements Callable {

        /*
         * (non-Javadoc)
         * @see java.util.concurrent.Callable#call()
         */
        @Override
        public Object call() throws Exception {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(100);
                System.out.println("aa" + i);
            }
            return "ok";
        }

    }

    static class TaskRunnble implements Runnable {

        /*
         * (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("aa" + i);
            }
        }

    }

}
