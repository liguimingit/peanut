/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.seda.task;

/**
 * 类TaskAware.java的实现描述：aware task接口
 * 
 * @author gm.l 2012-2-9 下午05:21:34
 */
public interface TaskAware {

    /**
     * 取到需要放入ThreadPoolExecutor的任务
     * 
     * @return Task
     */
    Task getTask();

    /**
     * task在spring配置文件的bean id
     * 
     * @return String
     */
    String getTaskBeanName();
}
