/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.seda.task;

import java.util.concurrent.Callable;

/**
 * 类TaskCallable.java的实现描述：具有返回值的任务接口，继承Callable<Object>。
 * 
 * @author liulin 2012-8-27 上午10:57:11
 */
public interface TaskCallable extends Callable<Object> {

    /**
     * 把本任务要处理的数据对象的toString方法返回即可。<br>
     * 如，return demoDO.toString();
     * 
     * @return String
     */
    @Override
    String toString();
}
