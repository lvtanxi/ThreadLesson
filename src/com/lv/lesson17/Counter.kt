package com.lv.lesson17

/**
 * Date: 2017-05-16
 * Time: 16:31
 * Description: 用volatile修饰的变量，线程在每次使用变量的时候，都会读取变量修改后的最的值。
 *                  volatile很容易被误用，用来进行原子性操作。其实不是volatile只能保证变量其他线程可见
 */
class Counter {
    companion object{
        var count=0
        @Volatile var count2=0
        @JvmStatic
        fun main(args: Array<String>) {
           for (index in 1..1000){
                Thread{
                    increment()
                }.start()
            }
            println("这是没有加Volatile的值：$count")
            //这里count每次执行的结果都不一样
            //很多人以为，这个是多线程并发问题，
            // 只需要在变量count之前加上volatile就可以避免这个问题，那我们在修改代码看看，看看结果是不是符合我们的期望
            for (index in 1..1000){
                Thread{
                    incr()
                }.start()
            }
            println("这是加了Volatile的值：$count2")
        }
        fun increment(){
            count ++
        }

        fun incr(){
            count2++
        }

    }
}