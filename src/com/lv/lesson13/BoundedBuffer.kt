package com.lv.lesson13

import java.util.*
import java.util.concurrent.locks.ReentrantLock

/**
 * Date: 2017-05-11
 * Time: 16:34
 * Description: Condition 实现多路由唤醒。每个方法都有两个锁
 */
class BoundedBuffer {
    private val lock = ReentrantLock()
    private val notFull = lock.newCondition()
    private val notEmpty = lock.newCondition()
    private val items = arrayOfNulls<Any>(100)
    private var putPtr = 0
    private var takePtr = 0
    private var count = 0

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val boundedBuffer = BoundedBuffer()
            for (index in 0..10){
                Thread{
                    println("添加数据")
                    boundedBuffer.put(Random().nextInt(3000))
                }.start()
                Thread{
                    println(boundedBuffer.take())
                }.start()
            }
        }
    }

    fun put(any: Any) {
        //添加数据的时候上锁
        lock.lock()
        while (count == items.size) //判断如果count等于数组的长度了，标识满了，为了防止再添加数据，就让未满的Condition等待
            notFull.await()
        items[putPtr] = any
        if (++putPtr == items.size) putPtr = 0 //这里是判断如果装的数据超过了100，就是新的一轮数据
        ++count
        notEmpty.signal()  // 既然有数据就唤醒取数据的线程
        //解除添加时候的锁
        lock.unlock()
    }

    fun take(): Any? {
        //添加获取数据的锁
        lock.lock()
        while (count==0) // 如果count为0的话，就表示没有数据，就需要让不为空的Condition等待
            notEmpty.await()
        val any=items[takePtr]
        if(++takePtr==items.size)takePtr=0  //这里是判断如果装的数据超过了100，就是新的一轮数据
        --count
        notFull.signal() //既然没有满，就要唤醒可以添加数据的Condition
        //解除获取数据的锁
        lock.unlock()
        return any
    }


}