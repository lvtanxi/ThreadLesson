package com.lv.lesson7

/**
 * Date: 2017-05-11
 * Time: 10:55
 * Description: 多个线程操作数据，为了避免同时操作数据的情况需要加锁
 */
class MultiThreadShareData {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val shareData = ShareData()
            Thread {
                println(Thread.currentThread().name)
                shareData.add()
            }.start()
            Thread {
                println(Thread.currentThread().name)
                shareData.sub()
            }.start()
        }
    }

    class ShareData {
        private var j: Int = 0
        @Synchronized fun add() = j++
        @Synchronized fun sub() = j--
    }
}