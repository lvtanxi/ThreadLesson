package com.lv.lesson8

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Date: 2017-05-11
 * Time: 13:22
 * Description: 线程池
 */
class ThreadPoolTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            //创建固定大小的线程池
            //val threadPool = Executors.newFixedThreadPool(3)
            //缓存线程，根据需要自动创建线程，对于60s没有用的线程要进行自动回收
            //val threadPool = executors.newCachedThreadPool()
            //单一线程池，线程死掉会创建一个新的
            val threadPool = Executors.newSingleThreadExecutor()
            for (i in 0..4) {
                threadPool.execute {
                    Thread.sleep((Math.random() * 5000).toLong())
                    for (index in 0..3) {
                        println("${Thread.currentThread().name} loop of $index of task $i")
                    }
                }
            }
            //任务执行完毕后自动关闭
            threadPool.shutdown()

            //立即停止线程运行,有sleep的时候会报错
            //threadPool.shutdownNow()

            //定时器
            Executors.newScheduledThreadPool(3)
                    .schedule({
                        println("bombing")
                    }, 10, TimeUnit.SECONDS)
            //固定频率
            Executors.newScheduledThreadPool(3)
                    .scheduleAtFixedRate({
                        println("固定频率 bombing")
                    }, 10, 2, TimeUnit.SECONDS)
        }
    }
}