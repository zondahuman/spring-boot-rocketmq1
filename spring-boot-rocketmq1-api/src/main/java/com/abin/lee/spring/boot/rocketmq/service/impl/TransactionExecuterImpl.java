package com.abin.lee.spring.boot.rocketmq.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionExecuter;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by abin on 2018/1/3 18:57.
 * spring-rocketmq1
 * com.abin.lee.spring.rocketmq.stub.service.impl
 * 执行本地事务
 * https://www.cnblogs.com/520playboy/p/6750023.html
 */
@Slf4j
public class TransactionExecuterImpl implements LocalTransactionExecuter {
    private AtomicInteger transactionIndex = new AtomicInteger(1);

    @Override
    public LocalTransactionState executeLocalTransactionBranch(final Message msg, final Object arg) {
        int value = transactionIndex.getAndIncrement();
        log.info("------------------------------------------------------------------------");
        log.info("--------------------------value = "+value+" ----------------------------------------------");
        log.info("------------------------------------------------------------------------");
        if (value == 0) {
            throw new RuntimeException("Could not find db");
        } else if (value == 1) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        } else if (value == 2) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }else{
            return LocalTransactionState.UNKNOW;
        }

//        return LocalTransactionState.UNKNOW;
    }
}
