/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.seda.handler;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.log4j.Logger;
import org.peanut.seda.MonitorThreadPoolTaskExecutor;

/**
 * 类LogUncaughtExceptionHandler.java的实现描述：UncaughtExceptionHandler的实现
 * <p>
 * Thread的run方法是不抛出任何检查型异常(checked exception)的,但是它自身却可能因为一个异常而被终止，
 * <tt>导致这个线程的终结。最麻烦的是，在线程中抛出的异常即使在主线程中使用try...catch也无法截获，<br/>
 * 因此可能导致一些问题出现，比如异常的时候无法回收一些系统资源，或者没有关闭当前的连接等等。 主线程之所以
 * 不处理子线程抛出的RuntimeException,是因为线程是异步的，子线程没结束，主线程可能已经结束了。</br> 
 * UncaughtExceptionHandler名字意味着处理未捕获的异常。更明确的说，它处理未捕获的运行时异常。 <br/>
 * 
 * @author liulin 2012-2-9 下午05:21:15
 * @see ThreadPoolTaskExecutor
 * @see MonitorThreadPoolTaskExecutor
 */

public class LogUncaughtExceptionHandler implements UncaughtExceptionHandler {

    private static final Logger exceptionLog = Logger.getLogger("threadPoolExceptionLogger");

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        exceptionLog.error(t.toString(), e);
    }

}
