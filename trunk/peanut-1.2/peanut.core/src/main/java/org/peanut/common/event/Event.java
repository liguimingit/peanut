/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.common.event;

import java.util.EventObject;

/**
 * 类Event.java的实现描述：事件基类.
 * 
 * @author gm.l 2012-2-10 下午04:57:41
 */
public class Event<E> extends EventObject {

    private static final long serialVersionUID = -1568642536506635292L;

    /**
     * 事件类型.
     */
    private String            type;

    /**
     * 事件所对应的数据对象.
     */
    private E                 data;

    /**
     * 重写默认构造.
     * 
     * @param source 最初发生 Event 的对象。
     * @param type 事件类型.
     * @param data 事件所对应的数据对象.
     */
    public Event(Object source, String type, E data){
        super(source);
        this.type = type;
        this.data = data;
    }

    /**
     * 获取事件的类型
     * 
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * 获取事件的数据对象.
     * 
     * @return
     */
    public E getData() {
        return data;
    }

    /*
     * (non-Javadoc)
     * @see java.util.EventObject#toString()
     */
    @Override
    public String toString() {
        return super.toString() + "[type= " + type + "]";
    }
}
