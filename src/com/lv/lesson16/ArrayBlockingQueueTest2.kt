package com.lv.lesson16

import java.util.concurrent.ArrayBlockingQueue

/**
 * Date: 2017-05-12
 * Time: 09:36
 * Description: 是这一个生产者和消费者的模式
 */
class ArrayBlockingQueueTest2 {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val runMan = RunMan()
            for (index in 0..100){
                Thread{  runMan.sub(index) }.start()
            }
            for (index in 0..10){
                 runMan.mai(index)
            }
        }
    }
    class RunMan{
        val subA=ArrayBlockingQueue<Int>(1)
        val subB=ArrayBlockingQueue<Int>(1)
        init {
            subB.put(2)
        }
        fun sub(num:Int){
            subA.put(1)
            for (index in 1..10){
                println("thi is sub run $num 的第$index 次" )
            }
            subB.take()
        }

        fun mai(num:Int){
            subB.put(2)
            for (index in 1..10){
                println("thi is main run $num 的第$index 次" )
            }
            subA.take()
        }
    }
}