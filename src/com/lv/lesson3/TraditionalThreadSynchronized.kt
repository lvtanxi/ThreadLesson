package com.lv.lesson3

/**
 * Date: 2017-05-10
 * Time: 16:06
 * Description: 传统的线程同步， Synchronized 所监视的对象是同一个就可以互斥
 *
 */
class TraditionalThreadSynchronized {
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

    /**
     * 为方法添加同步锁,多方法的时候同步锁使用的对象是同一个
     */
    private class Outputer {

        fun output(name: String) {
            synchronized(this) {
                name.toCharArray().forEach(::print)
                println()
            }
        }

        @Synchronized fun output2(name: String) {
            name.toCharArray().forEach(::print)
            println()
        }
    }
}