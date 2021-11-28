/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.seda.event;

import java.util.ArrayList;
import java.util.List;

import org.peanut.common.event.Event;
import org.peanut.common.event.EventDataObject;
import org.peanut.common.event.EventListener;
import org.peanut.common.event.EventService;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * 类DefaultEventService.java的实现描述：EventService的实现，简化.管理多个Listener的服务类。上层触发调用 类。
 * 
 * @author gm.l 2012-2-9 下午05:16:58
 * @see EventService
 * @see DefaultEventService
 */
public class DefaultEventService<T extends EventDataObject> implements EventService<T> {

    private List<EventListener<T>> registry = new ArrayList<EventListener<T>>();

    /*
     * (non-Javadoc)
     * @see com.jd.common.event.EventService#addEventListener(com.jd.common.event.EventListener)
     */
    @Override
    public void addEventListener(EventListener<T> eventListener) {
        Assert.notNull(eventListener, "eventListener is null!");
        registry.add(eventListener);
    }

    /*
     * (non-Javadoc)
     * @see com.jd.common.event.EventService#removeEventListener(com.jd.common.event.EventListener)
     */
    @Override
    public void removeEventListener(EventListener<T> eventlistener) {
        // TODO Auto-generated method stub
        // do nothing
        throw new UnsupportedOperationException("The method is not to unsupported.");
    }

    /*
     * (non-Javadoc)
     * @see com.jd.common.event.EventService#publishEventImmediately(com.jd.common.event.Event)
     */
    @Override
    public void publishEventImmediately(Event<T> event) {

        if (!CollectionUtils.isEmpty(registry)) {
            for (EventListener<T> eventListener : registry) {
                // before
                eventListener.beforeEvent(event);

                // event
                eventListener.onEvent(event);

                // after
                eventListener.afterEvent(event);
            }
        }

    }

    /*
     * (non-Javadoc)
     * @see com.jd.common.event.EventService#publishEventHesitate(com.jd.common.event.Event)
     */
    @Override
    public void publishEventHesitate(Event<T> event) {
        // TODO Auto-generated method stub
        // do nothing
        throw new UnsupportedOperationException("The method is not to unsupported.");
    }

    /**
     * @param registry
     */
    public void setEventListener(List<EventListener<T>> registry) {
        this.registry = registry;
    }

    /**
     * @param registry
     */
    public List<EventListener<T>> getEventListener() {
        return registry;
    }
}
