package com.abin.lee.spring.boot.rocketmq.service.impl;

/**
 * Created by abin on 2018/1/3 18:59.
 * spring-rocketmq1
 * com.abin.lee.spring.rocketmq.stub.service.impl
 * 未决事务，服务器回查客户端
 * TransactionCheckListenerImpl--未决事务，服务器回查客户端(目前已经被阉割啦)
 * https://www.cnblogs.com/520playboy/p/6750023.html
 */

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionCheckListener;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.atomic.AtomicInteger;

public class TransactionCheckListenerImpl implements TransactionCheckListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);

    @Override
    public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
        System.out.println("-----------------------------LocalTransactionState-----checkLocalTransactionState------------------------------------------------");
        System.out.printf("server checking TrMsg %s%n", msg);

        int value = transactionIndex.getAndIncrement();
        if ((value % 6) == 0) {
            throw new RuntimeException("Could not find db");
        } else if ((value % 5) == 0) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        } else if ((value % 4) == 0) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }

        return LocalTransactionState.UNKNOW;
    }
}
