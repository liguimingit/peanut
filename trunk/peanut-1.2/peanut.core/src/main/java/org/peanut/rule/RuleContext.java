/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.rule;

/**
 * 类RuleContext.java的实现描述：通用的规则所处环境的接口，不同业务场景下，规则的环境是不同的，所以具体业务要实现这个接口，设置属于的自己的环境
 * 
 * @author gm.l 2012-2-13 下午01:27:25
 */
public interface RuleContext {

    /**
     * 是否已经根据规则完成业务的处理，如果为true将终止.
     * 
     * @return true/false
     */
    boolean isFinish();

    /**
     * 设置规则是否完成业务的处理，true:完成结束执行，false：继续执行下面的Rule.
     * 
     * @param finish
     */
    void setFinish(boolean finish);
}
