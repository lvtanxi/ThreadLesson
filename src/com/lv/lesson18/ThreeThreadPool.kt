package com.lv.lesson18

import java.util.concurrent.*

/**
 * Date: 2017-05-17
 * Time: 09:13
 * Description: Java线程池类ThreadPoolExecutor、ScheduledThreadPoolExecutor及Executors工厂类
 */
class ThreeThreadPool {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            /**
             * - corePoolSize：线程池维护线程的最少数量
             *- maximumPoolSize：线程池维护线程的最大数量
             *- keepAliveTime： 线程池维护线程所允许的空闲时间
             *- unit： 线程池维护线程所允许的空闲时间的单位
             *- workQueue： 线程池所使用的缓冲队列
             *- handler： 线程池对拒绝任务的处理策略
             */
            val threadPoolExecutor = ThreadPoolExecutor(5, 100, 5000, TimeUnit.MILLISECONDS, LinkedBlockingDeque<Runnable>())
            /**
             * ScheduledThreadPoolExecutor继承自ThreadPoolExecutor，构造参数很简单，只有3个：
             *1. int corePoolSize：线程池维护线程的最少数量
             *2. ThreadFactory threadFactory：线程工程类，线程池用它来制造线程
             *3. RejectedExecutionHandler handler：线程池对拒绝任务的处理策略
             */
            val scheduledThreadPoolExecutor = ScheduledThreadPoolExecutor(2, Executors.defaultThreadFactory(), ThreadPoolExecutor.AbortPolicy())
            /**
             * Executors工厂类，它可以帮助我们很方便的创建各种类型ExecutorService线程池，Executors一共可以创建下面这四类线程池：
             *1. newCachedThreadPool 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
             *2. newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
             *3. newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
             *4. newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
             */
            val newCachedThreadPool = Executors.newCachedThreadPool()
            val newFixedThreadPool = Executors.newFixedThreadPool(3)
            val newScheduledThreadPool = Executors.newScheduledThreadPool(3)
            val newSingleThreadExecutor = Executors.newSingleThreadExecutor()

        }
    }
}