package com.lv.lesson13

import java.util.concurrent.locks.ReentrantLock

/**
 * Date: 2017-05-11
 * Time: 15:50
 * Description: 利用Condition实现ReentrantLock锁中的线程通信
 *                   Condition与wait的区别是Condition可以实现wait的所有功能，而且还能
 *                   实现多路唤醒
 */
class ConditionCommunication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val business = Business()
            Thread(Runnable {
                run {
                    (1..50).forEach {
                        business.sub(it)
                    }
                }
            }).start()
            (1..50).forEach {
                business.main(it)
            }
        }
    }

    private class Business {
        private var bShouldSub = true
        private val lock=ReentrantLock()
        private val condition=lock.newCondition()
        fun sub(num: Int) {
            lock.lock()
            while (!bShouldSub)
                condition.await()
            for (j in 1..10) {
                println("sub run $num 的第 $j 次")
            }
            bShouldSub = false
            condition.signal()
            lock.unlock()
        }

        fun main(num: Int) = synchronized(lock) {
            lock.lock()
            while (bShouldSub)
                condition.await()
            for (j in 1..100) {
                println("main run $num 的第 $j 次")
            }
            bShouldSub = true
            condition.signal()
            lock.unlock()
        }

    }
}