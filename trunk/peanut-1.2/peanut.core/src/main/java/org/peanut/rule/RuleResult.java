/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.rule;


/**
 * 类RuleResult.java的实现描述：通用的规则返回结果接口
 * 
 * @author liulin 2012-2-13 下午01:27:47
 */
public interface RuleResult {

    /**
     * 规则名称
     */
    String getRuleName();

    /**
     * @param ruleName the ruleName to set
     */
    void setRuleName(String ruleName);

}
