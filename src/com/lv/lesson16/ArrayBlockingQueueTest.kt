package com.lv.lesson16

import java.util.concurrent.ArrayBlockingQueue

/**
 * Date: 2017-05-12
 * Time: 09:06
 * Description: 阻塞队列，拿数据和放数据互斥。只有有数据才能拿，相反，只有有空位才能放(生产者与消费者模式)
 */

class ArrayBlockingQueueTest{
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val blockingQueue = ArrayBlockingQueue<Long>(3)
            for (index in 0..1){
                Thread{
                    while (true){
                        val time = (Math.random()*1000).toLong()
                        println("${Thread.currentThread().name}准备放数据")
                        Thread.sleep(time)
                        blockingQueue.put(time)
                        println("${Thread.currentThread().name}已经放入了数据， 队列中有${blockingQueue.size}个数据")
                    }
                }.start()
                Thread{
                    while (true){
                        println("${Thread.currentThread().name}准备获取数据")
                        Thread.sleep(1000)
                        println("${Thread.currentThread().name}获取到的数据是${  blockingQueue.take()}， 队列中还有${blockingQueue.size}个数据")
                    }
                }.start()
            }
        }
    }
}