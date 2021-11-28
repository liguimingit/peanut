/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.seda.handler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

/**
 * 类LogRejectedExecutionHandler.java的实现描述：<br/>
 * 当线程满,队列也满的情况,<br/>
 * 不处理被拒绝的线程,这里记录被抛弃的数据到日志里.
 * 
 * @author gm.l 2012-2-9 下午05:20:57
 */
public class LogRejectedExecutionHandler implements RejectedExecutionHandler {

    private static final Logger log    = Logger.getLogger("logRejectedTaskLogger");
    private static final Logger bizLog = Logger.getLogger("logRejectedTaskLogger");

    /**
     * 对于被抛弃的数据，记录到日志<br>
     * 后期项目上线后，检查，如果有，则后续操作<br/>
     * 注：Task一定要实现toString方法，方便定问题定位。
     */
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        try {
            if (!e.isShutdown()) {
                CustomizableThreadFactory ctf = (CustomizableThreadFactory) e.getThreadFactory();
                String tnp = ctf.getThreadNamePrefix();

                bizLog.info("[thread=" + r + ", threadNamePrefix=" + tnp + "]");
            }

        } catch (Exception ex) {
            log.error(ex);
        }

    }

}
