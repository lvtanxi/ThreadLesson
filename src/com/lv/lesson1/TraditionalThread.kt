package com.lv.lesson1

/**
 * Date: 2017-05-10
 * Time: 14:45
 * Description: 传统通过Thread或者Runable的线程
 */
class TraditionalThread {
    companion object{

        @JvmStatic
        fun main(args: Array<String>) {
            Thread{
                    var index=0
                    while (index<10){
                        Thread.sleep(500)
                        println(Thread.currentThread().name)
                        index++
                    }
            }.start()

            Thread{
                    var index=0
                    while (index<10){
                        Thread.sleep(500)
                        println("my name is ${Thread.currentThread().name}")
                        index++
                    }
            }.start()
        }
    }
}