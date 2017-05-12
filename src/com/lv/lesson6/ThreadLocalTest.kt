package com.lv.lesson6

import java.util.*

/**
 * Date: 2017-05-11
 * Time: 09:49
 * Description: ThreadLocal利用实现线程数据共享
 */
class ThreadLocalTest {
    companion object {
        var data: Int = 0
        //单个数据共享可以不用创建实体
        val threadData=ThreadLocal<User>()
        @JvmStatic
        fun main(args: Array<String>) {
            for (index in 0..1) {
                Thread{
                        data = Random().nextInt()
                        threadData.set(User(Thread.currentThread().name, data))
                        println("${Thread.currentThread().name} has put data :$data")
                        A().get()
                        B().get()
                    }.start()
            }
        }
    }

    private class A {
        fun get() = println("A from ${Thread.currentThread().name} has put data :${threadData.get()}")
    }

    private class B {
        fun get() = println("B from ${Thread.currentThread().name} has put data :${threadData.get()}")
    }

    data class User(var name:String,var age:Int)

}