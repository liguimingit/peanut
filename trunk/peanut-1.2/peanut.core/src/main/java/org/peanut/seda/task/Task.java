/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.seda.task;

/**
 * 类Task.java的实现描述： 任务接口，实现者必须实现此接口的两个方法<br>
 * 在spring里配为多态，以便使用spring提供的注入功能，继承Runnalbe。
 * 
 * @author liulin 2012-2-9 下午05:21:26
 */
public interface Task extends Runnable {

    /**
     * 把本任务要处理的数据对象的toString方法返回即可。<br>
     * 如，return demoDO.toString();
     * 
     * @return String
     */
    @Override
    String toString();
}
