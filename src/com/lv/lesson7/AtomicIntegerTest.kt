package com.lv.lesson7

import java.util.concurrent.atomic.AtomicInteger

/**
 * Date: 2017-05-15
 * Time: 14:35
 * Description: AtomicInteger
 * J2SE 5.0提供了一组atomic class来帮助我们简化同步处理。
 * 基本工作原理是使用了同步synchronized的方法实现了对一个long,
 * integer, 对象的增、减、赋值（更新）操作.
 * 比如对于++运算符AtomicInteger可以将它持有的integer 能够atomic 地递增。
 * 在需要访问两个或两个以上 atomic变量的程序代码
 * （或者是对单一的atomic变量执行两个或两个以上的操作）
 * 通常都需要被synchronize以便两者的操作能够被当作是一个atomic的单元。
 *
 */
class AtomicIntegerTest {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val atomicInteger = AtomicInteger()
            atomicInteger.set(100)

            Thread{
                while (true){
                    Thread.sleep((Math.random()*5000).toLong())
                    println("${Thread.currentThread().name} 添加数据")
                    val incrementAndGet = atomicInteger.incrementAndGet()
                    println("${Thread.currentThread().name} 添加后的数据为:$incrementAndGet")
                }
            }.start()

            Thread{
                while (true){
                    Thread.sleep((Math.random()*5000).toLong())
                    println("${Thread.currentThread().name} 减少数据")
                    val decrementAndGet = atomicInteger.decrementAndGet()
                    println("${Thread.currentThread().name} 减少后的数据为:$decrementAndGet")
                }
            }.start()
        }
    }
}