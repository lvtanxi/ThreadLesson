package com.lv.lesson18

/**
 * Date: 2017-05-17
 * Time: 11:51
 * Description: 检测线程是否有锁
 */
class ThreadHoldsLock {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val obj=java.lang.Object()
            Thread{
                synchronized(obj){
                    println("在子线程中打印：${Thread.holdsLock(obj)}")
                }
            }.start()
            println("在主线程中打印：${Thread.holdsLock(obj)}")
        }
    }
}