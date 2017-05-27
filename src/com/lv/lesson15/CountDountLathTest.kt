package com.lv.lesson15

import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

/**
 * Date: 2017-05-11
 * Time: 17:30
 * Description:  CountDountLath 犹如倒计时计数器，调用CountDowunLatch对象的countDown方法就将计数器减少1
 *                  ，当计数到达0时，则所有等待者或单个等待者开始执行。像运动会，一个项目要所有运动员完成了才会公布成绩
 *                  CountDountLath与CyclicBarrier的区别是，CyclicBarrier可以重复使用
 */
class CountDountLathTest {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val threadPool = Executors.newCachedThreadPool()
            val cdOrder=CountDownLatch(1)
            val cdAnswer=CountDownLatch(3)
            for (index in 0..2){
                threadPool.execute {
                    println("线程${Thread.currentThread().name}正准备接受命令")
                    cdOrder.await() //等到主线程的cdOrder调用countDown()方法把数字变成0，才能走
                    println("线程${Thread.currentThread().name}已准备接受命令")
                    Thread.sleep((Math.random()*10000).toLong())
                    println("线程${Thread.currentThread().name}回应命令处理结果")
                    cdAnswer.countDown()
                }
            }
            Thread.sleep((Math.random()*10000).toLong())
            println("线程${Thread.currentThread().name}即将发布命令")
            cdOrder.countDown()
            println("线程${Thread.currentThread().name}已经发送命令，正在等待结果")
            cdAnswer.await()
            println("线程${Thread.currentThread().name}已收到所有响应结果")
            threadPool.shutdown()
        }
    }
}