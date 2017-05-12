package com.lv.lesson4

/**
 * Date: 2017-05-10
 * Time: 16:57
 * Description: 传统线程通信. Business中的代码是关键
 */
class TraditionalThreadCommunication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val business = Business()
            Thread {
                (1..50).forEach {
                    business.sub(it)
                }
            }.start()
            (1..50).forEach {
                business.main(it)
            }
        }
    }

    private class Business {
        private var bShouldSub = true
        private val lock = java.lang.Object()
        //synchronized(lock) 是因为kotlin的Any没有wait方法
        fun sub(num: Int) = synchronized(lock) {
            while (!bShouldSub)
                lock.wait()
            for (j in 1..10) {
                println("sub run $num 的第 $j 次")
            }
            bShouldSub = false
            lock.notify()
        }

        fun main(num: Int) = synchronized(lock) {
            while (bShouldSub)
                lock.wait()
            for (j in 1..100) {
                println("main run $num 的第 $j 次")
            }
            bShouldSub = true
            lock.notify()
        }

    }
}