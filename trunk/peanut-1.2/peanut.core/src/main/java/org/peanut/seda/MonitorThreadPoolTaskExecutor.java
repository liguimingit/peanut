/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.seda;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.peanut.seda.handler.LogUncaughtExceptionHandler;

/**
 * 类MonitorThreadPoolTaskExecutor.java的实现描述：带有监控功能的ThreadPoolTaskExecutor。
 * 
 * @author liulin 2012-2-9 下午05:21:51
 * @see ThreadPoolTaskExecutor
 */
public class MonitorThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private static final ThreadPoolWatcher tpWatcher                = new ThreadPoolWatcher();
    static {
        Thread w = new Thread(tpWatcher, "ThreadPoolWatcher");
        w.setDaemon(true);
        w.start();
    }

    /**
     * 用以替换默认Thread.uncaughtExceptionHandler的实现，把未捕获的异常记录到日志里
     */
    private UncaughtExceptionHandler       uncaughtExceptionHandler = new LogUncaughtExceptionHandler();

    /**
     * ThreadPoolTaskExecutor初始化完成后，注册自己到监控任务里
     */
    @Override
    public void initialize() {
        super.initialize();
        tpWatcher.registerThreadPoolTaskExecutor(this);
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = super.newThread(runnable);
        thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        return thread;
    }

    /**
     * @param uncaughtExceptionHandler the uncaughtExceptionHandler to set
     */
    public void setUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;

    }
}

/**
 * 类MonitorThreadPoolTaskExecutor.java的实现描述：监控线程，用于监控线程池的相关状态
 * 
 * @author liulin 2012-2-11 上午02:29:47
 */
class ThreadPoolWatcher implements Runnable {

    private static final Logger         log      = Logger.getLogger("threadPoolDumpLogger");
    private Set<ThreadPoolTaskExecutor> tpteList = new HashSet<ThreadPoolTaskExecutor>();
    private boolean                     isKill   = false;

    public ThreadPoolWatcher(){
        log.info("start ThreadPoolWatcher monitor...");
    }

    public void registerThreadPoolTaskExecutor(ThreadPoolTaskExecutor tpte) {
        tpteList.add(tpte);
    }

    public void kill() {
        isKill = true;
    }

    @Override
    public void run() {

        while (!isKill) {
            try {
                // sleep 1m
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                log.error(e);
                break;
            }

            for (ThreadPoolTaskExecutor tpte : tpteList) {
                String tnp = tpte.getThreadNamePrefix();
                log.warn("----------");
                log.warn(tnp + "_ActiveThreadsCount=" + tpte.getActiveCount());
                log.warn(tnp + "_CompletedTaskCount=" + tpte.getThreadPoolExecutor().getCompletedTaskCount());
                log.warn(tnp + "_QueueSize=" + tpte.getThreadPoolExecutor().getQueue().size());
            }
        }
    }

}
