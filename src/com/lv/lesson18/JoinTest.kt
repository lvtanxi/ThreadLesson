package com.lv.lesson18

/**
 * Date: 2017-05-17
 * Time: 13:48
 * Description: 利用join有三个线程T1，T2，T3，怎么确保它们按顺序执行
 */
class JoinTest {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val thread1 = Thread {
                println("this is thread 1")
            }
            val thread2 = Thread{
                thread1.join()
                println("this is thread 2")
            }
            val thread3 = Thread {
                thread2.join()
                println("this is thread 3")
            }
            thread1.start()
            thread2.start()
            thread3.start()
        }
    }
}