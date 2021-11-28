/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.seda;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.peanut.seda.monitor.MonitorKeys;

/**
 * 类CustomThreadPoolExecutor.java的实现描述：自定义扩展ThreadPoolExecutor，覆写指定方法
 * 
 * @author liulin 2012-2-9 下午05:21:42
 */
public class CustomThreadPoolExecutor extends ThreadPoolExecutor {

    private static final Logger exceptionLog = Logger.getLogger("threadPoolExceptionLogger");

    protected String            monitorTaskCountKey;

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
                                    RejectedExecutionHandler handler, String threadNamePrefix){
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        this.monitorTaskCountKey = threadNamePrefix + MonitorKeys.TASK_COUNT_SUFFIX;

        // TODO do nothing
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t != null) {
            // log it
            exceptionLog.error(String.format("Task=%s", r), t);
        } else {
            // do noting
        }
    }

}
