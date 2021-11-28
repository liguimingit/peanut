/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.common.event;

/**
 * 类EventListener.java的实现描述：事件监听基础服务接口,此类是具体的Listener和触发Task的监听类。
 * 
 * @author gm.l 2012-2-10 下午04:55:18
 * @see 添加Listener beforeEvent,afterEvent方法。2012.11.27 16:01
 */
public interface EventListener<T> {

    /**
     * 监听事件所触发的业务方法.
     * 
     * @param event
     */
    void onEvent(Event<T> event);

    /**
     * 设置Task依赖另一个Task执行完毕后方可执行。否则一直wait();
     * 
     * @param dependTaskBeanName
     */
    void setDependTaskBeanName(String dependTaskBeanName);

    /**
     * 获取监听名称
     * 
     * @return
     */
    String getListenerName();

    /**
     * 获取任务名称
     * 
     * @return
     */
    String getTaskBeanName();

    /**
     * 在事件执行之前，调用些方法。
     * 
     * @param event
     */
    void beforeEvent(Event<T> event);

    /**
     * 在事件执行之后，调用 些方法。
     * 
     * @param event
     */
    void afterEvent(Event<T> event);
}
