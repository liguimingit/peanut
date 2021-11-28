/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.rule;

import java.util.List;

/**
 * 类RuleEngine.java的实现描述：通用的规则引擎接口，在RuleContext下经过每条规则的过滤和处理，最终获得处理结果(RuleResult)
 * 
 * @author liulin 2012-2-13 下午01:27:36
 */
public interface RuleEngine<E extends RuleResult, T extends RuleContext> {

    /**
     * 执行所有的Rule,最后返回规则结果。
     * 
     * @see context.setFinish(true/false),true:规则执行结束，false：继续执行下面的Rule.
     * @param context 规则的上下文对象.
     * @return
     */
    List<E> process(T context);

    /**
     * 获取所有注入规则引擎的规则。
     * 
     * @return
     */
    List<Rule<E, T>> getRuleList();

}
