package com.lv.lesson10

import java.util.concurrent.locks.ReentrantLock

/**
 * Date: 2017-05-12
 * Time: 16:07
 * Description: 可重入锁 ReentrantLock，实现方法互斥 。跟Synchronized用法差不多
 */
class LockTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val outputer = Outputer()
            Thread {
                while (true) {
                    Thread.sleep(10)
                    outputer.output("zhangxiaoxiang")
                }
            }.start()
            Thread {
                while (true) {
                    Thread.sleep(10)
                    outputer.output2("lihuoming")
                }
            }.start()
        }
    }
    private class Outputer {
        val lock = ReentrantLock() //获取
        fun output(name: String) {
            lock.lock()
            try {
                name.toCharArray().forEach(::print)
                println()
            } finally {
                lock.unlock()
            }
        }
        fun output2(name: String) {
            lock.lock()
            try {
                name.toCharArray().forEach(::print)
                println()
            } finally {
                lock.unlock()
            }
        }
    }
}