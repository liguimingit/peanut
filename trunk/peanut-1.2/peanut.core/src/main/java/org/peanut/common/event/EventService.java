/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.common.event;

/**
 * 类EventService.java的实现描述：管理事件监听基础服务接口,对事件和Listener管理的集合.触发者.<br/>
 * 管理多个Listener的服务类。上层触发调用 类。
 * 
 * @see EventListener
 * @see Event
 * @author liulin 2012-2-10 下午04:51:20
 */
public interface EventService<T> {

    /**
     * 注册事件监听器.
     * 
     * @param eventlistener
     */
    void addEventListener(EventListener<T> eventListener);

    /**
     * 删除已注册的事件监听器.
     * 
     * @param eventlistener
     */
    void removeEventListener(EventListener<T> eventListener);

    /**
     * 发布事件通知，立即执行.
     * 
     * @param event
     */
    void publishEventImmediately(Event<T> event);

    /**
     * 发布事件通知，异步执行.
     * 
     * @param event
     */
    void publishEventHesitate(Event<T> event);
}
