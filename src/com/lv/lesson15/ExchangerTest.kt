package com.lv.lesson15

import java.util.concurrent.Exchanger
import java.util.concurrent.Executors

/**
 * Date: 2017-05-12
 * Time: 08:51
 * Description: 用于实现两个人之间的数据交换，每个人在完成一定的事务后相与对方交互数据，第一个先拿出数据的人
 *                  将一直等待第二个人拿着数据到来时，才能彼此交换数据
 */
class ExchangerTest {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val threadPool = Executors.newCachedThreadPool()
            val exchanger = Exchanger<String>()
            threadPool.execute {
                println("线程${Thread.currentThread().name}正在把数据 张孝祥 还出去")
                Thread.sleep((Math.random()*10000).toLong())
                println("线程${Thread.currentThread().name}换回的数据为${exchanger.exchange("张孝祥")}")
            }

            threadPool.execute {
                println("线程${Thread.currentThread().name}正在把数据 罗永浩 还出去")
                Thread.sleep((Math.random()*10000).toLong())
                println("线程${Thread.currentThread().name}换回的数据为${exchanger.exchange("罗永浩")}")
            }


            threadPool.shutdown()
        }
    }
}