package com.lv.lesson15

import java.util.concurrent.CyclicBarrier
import java.util.concurrent.Executors

/**
 * Date: 2017-05-11
 * Time: 17:18
 * Description:  CyclicBarrier 有点像客车。需要等到全部人员到齐了才会发车
 */
class CyclicBarrierTest {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val threadPool = Executors.newCachedThreadPool()
            val cb=CyclicBarrier(3)
            for(index in 0..2){
                threadPool.execute {
                    Thread.sleep((Math.random()*10000).toLong())
                    println("线程${Thread.currentThread().name}即将到达集合地点1，当前已有${cb.numberWaiting}个人")
                    cb.await()
                    Thread.sleep((Math.random()*10000).toLong())
                    println("线程${Thread.currentThread().name}即将到达集合地点2，当前已有${cb.numberWaiting}个人")
                    cb.await()
                    Thread.sleep((Math.random()*10000).toLong())
                    println("线程${Thread.currentThread().name}即将到达集合地点3，当前已有${cb.numberWaiting}个人")
                    cb.await()
                }
            }
            threadPool.shutdown()
        }
    }
}