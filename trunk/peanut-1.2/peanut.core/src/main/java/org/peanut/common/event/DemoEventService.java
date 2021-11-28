/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.common.event;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.peanut.common.event.util.ClassUtils;
import org.springframework.util.Assert;

/**
 * 类DefaultEventService.java的实现描述：默认事件服务类实现.
 * 
 * @see com.jd.common.event.EventService
 * @author liulin 2012-2-10 下午05:13:12
 */
public class DemoEventService<T extends EventDataObject> implements EventService<T> {

    /**
     * 存储所有注册的监听器.
     */
    private final Map<Class<?>, List<EventListener<T>>> reqistry = new ConcurrentHashMap<Class<?>, List<EventListener<T>>>();

    public DemoEventService(){
    }

    /**
     * 设置多个监听,
     * 
     * @param eventListeners list.
     */
    public void setEventListeners(List<EventListener<T>> eventListeners) {
        Assert.notNull(eventListeners, "eventListeners is null!");
        EventListener<T> eventListener;
        for (Iterator<EventListener<T>> iter = eventListeners.iterator(); iter.hasNext(); addEventListener(eventListener)) {
            eventListener = iter.next();
        }

    }

    /*
     * (non-Javadoc)
     * @see com.jd.common.event.EventService#addEventListener(com.jd.common.event.EventListener)
     */
    @Override
    public void addEventListener(EventListener<T> e) {
        Assert.notNull(e, "eventListener is null!");
        Class<?> cls = ClassUtils.getGenericClass(e.getClass());
        List<EventListener<T>> eventListeners = reqistry.get(cls);
        if (eventListeners == null) {
            eventListeners = new CopyOnWriteArrayList<EventListener<T>>();
            reqistry.put(cls, eventListeners);
        }
        eventListeners.add(e);
    }

    /*
     * (non-Javadoc)
     * @see com.jd.common.event.EventService#removeEventListener(com.jd.common.event.EventListener)
     */
    @Override
    public void removeEventListener(EventListener<T> e) {

        Assert.notNull(e, "eventListener is null!");
        List<EventListener<T>> eventListeners = reqistry.get(ClassUtils.getGenericClass(e.getClass()));
        if (eventListeners != null) {
            eventListeners.remove(e);
        }

    }

    /*
     * (non-Javadoc)
     * @see com.jd.common.event.EventService#publishEventImmediately(com.jd.common.event.Event)
     */
    @Override
    public void publishEventImmediately(Event<T> event) {

        Object data = event.getData();
        if (data == null) return;
        List<EventListener<T>> eventListeners = reqistry.get(data.getClass());
        if (eventListeners != null) {
            EventListener<T> eventListener;
            for (Iterator<EventListener<T>> iter = eventListeners.iterator(); iter.hasNext(); eventListener.onEvent(event)) {
                eventListener = iter.next();
            }

        }

    }

    /*
     * (non-Javadoc)
     * @see com.jd.common.event.EventService#publishEventHesitate(com.jd.common.event.Event)
     */
    @Override
    public void publishEventHesitate(final Event<T> event) {

        Object data = event.getData();
        if (data == null) return;
        List<EventListener<T>> eventListeners = reqistry.get(data.getClass());
        if (eventListeners != null) {
            ExecutorService executor;
            for (final EventListener<T> listener : eventListeners) {
                executor = Executors.newCachedThreadPool();
                executor.execute(new Runnable() {

                    @Override
                    public void run() {
                        listener.onEvent(event);
                    }
                });
            }

        }
    }
}
