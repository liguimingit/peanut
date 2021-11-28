/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.rule;

/**
 * 类Rule.java的实现描述：通用规则接口，具体规则实现该接口即可
 * 
 * @author gm.l 2012-2-13 下午01:27:10
 */
public interface Rule<E extends RuleResult, T extends RuleContext> {

    /**
     * 在RuleContext下执行规则，没有返回值，可以将操作结果定义到context中<br/>
     * 
     * @see context.setFinish(true/false),true:规则执行结束，false：继续执行下面的Rule.
     * @param context
     * @return context验证失败，返回null.
     */
    E execute(T context);

    /**
     * 设置规则是否完成业务的处理，true:完成结束执行，false：继续执行下面的Rule.
     * 
     * @see 可通过Spring配置文件，配置注入属性。<property name="finish" value="false" /> </br>
     * 或者通过，子类的context.setFinish(true/false),硬编码方式设置.
     */
    void setFinish(boolean finish);

}
