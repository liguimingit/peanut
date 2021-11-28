/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.seda.event;

import org.apache.log4j.Logger;
import org.peanut.common.event.Event;
import org.peanut.common.event.EventDataObject;
import org.peanut.common.event.EventListener;
import org.peanut.seda.task.Task;
import org.peanut.seda.task.TaskAware;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 类EventListenerTaskAware.java的实现描述：实现了TaskAware的Event Listener,Task没有返回值。<br/>
 * 此类是具体的Listener和触发Task的监听类。
 * 
 * @author gm.l 2012-2-9 下午05:19:27
 * @see TaskAware
 * @see BeanFactoryAware
 * @see EventListener
 * @see EventListenerCallableAware
 */

public abstract class EventListenerTaskAware<T extends EventDataObject, E extends Task> implements EventListener<T>, BeanFactoryAware, TaskAware {

    private static final Logger logger = Logger.getLogger(EventListenerTaskAware.class);

    BeanFactory                 beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 取到需要放入ThreadPoolExecutor的任务
     * 
     * @return Task
     */
    @Override
    @SuppressWarnings("unchecked")
    public E getTask() {
        try {
            return (E) beanFactory.getBean(getTaskBeanName());
        } catch (Exception e) {
            logger.error("EventListenerTaskAware.getTask(),error.", e);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.jd.peanut.common.event.EventListener#beforeEvent(com.jd.peanut.common.event.Event)
     */
    @Override
    public void beforeEvent(Event<T> event) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see com.jd.peanut.common.event.EventListener#afterEvent(com.jd.peanut.common.event.Event)
     */
    @Override
    public void afterEvent(Event<T> event) {
        // TODO Auto-generated method stub

    }

}
