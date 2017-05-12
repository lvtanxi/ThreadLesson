package com.lv.lesson11

import java.util.*
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * Date: 2017-05-12
 * Time: 16:05
 * Description:
 */
class ReadWriteTest {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val queue3 = Queue3()
            for (index in 0..2){
                Thread{
                    while (true){
                        queue3.get()
                    }
                }.start()
                Thread{
                    while (true){
                        queue3.put(Random().nextInt(1000))
                    }
                }.start()
            }
        }
    }
    class Queue3 {
        private var data=0
        //读写锁
        private val rwl= ReentrantReadWriteLock()

        /**
         * 加读取的锁
         */
        fun get(){
            rwl.readLock().lock()
            println("${Thread.currentThread().name}我准备读数据了：")
            Thread.sleep((Math.random()*1000).toLong())
            println("${Thread.currentThread().name}我读取到的数值为：$data")
            rwl.readLock().unlock()
        }
        /**
         * 加写入的锁
         */
        fun put(num:Int){
            rwl.writeLock().lock()
            println("${Thread.currentThread().name}我准备写数据了：")
            this.data=num
            Thread.sleep((Math.random()*1000).toLong())
            println("${Thread.currentThread().name}我写了后的数值为：$data")
            rwl.writeLock().unlock()
        }
    }
}