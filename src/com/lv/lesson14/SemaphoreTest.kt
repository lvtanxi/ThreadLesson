package com.lv.lesson14

import java.util.concurrent.Executors
import java.util.concurrent.Semaphore

/**
 * Date: 2017-05-11
 * Time: 16:53
 * Description: 信号灯。可以维护当前访问自身的线程个数，并提供了同步机制。例子：上厕所
 */
class SemaphoreTest {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val threadPool = Executors.newCachedThreadPool()
            val sp = Semaphore(3)//设置信号灯的数量
            for (index in 1..10){
                threadPool.execute{
                    sp.acquire() //获取一个信号灯，availablePermits返回被获取了的信号灯个数
                    println("线程${Thread.currentThread().name}进入，当前已经有${3-sp.availablePermits()}被获取了")
                    Thread.sleep((Math.random()*10000).toLong())
                    println("线程${Thread.currentThread().name}即将离开")
                    sp.release() //释放资源，让其他线程可以获取
                }
            }
            threadPool.shutdown()
        }
    }
}