package com.lv.lesson5

import java.util.*
import kotlin.collections.HashMap

/**
 * Date: 2017-05-10
 * Time: 17:41
 * Description: 利用Map实现线程范围内共享数据
 */
class ThreadScopeShareData {
    companion object {
        var data: Int = 0
        val threadData=HashMap<String,Int>()
        @JvmStatic
        fun main(args: Array<String>) {
            for (index in 0..1) {
                Thread(Runnable {
                    run {
                        data = Random().nextInt()
                        threadData.put(Thread.currentThread().name, data)
                        println("${Thread.currentThread().name} has put data :$data")
                        A().get()
                        B().get()
                    }
                }).start()
            }
        }
    }

    private class A {
        fun get() = println("A from ${Thread.currentThread().name} has put data :${threadData[Thread.currentThread().name]}")
    }

    private class B {
        fun get() = println("B from ${Thread.currentThread().name} has put data :${threadData[Thread.currentThread().name]}")
    }

}