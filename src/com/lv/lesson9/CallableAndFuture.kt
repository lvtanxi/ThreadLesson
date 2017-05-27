package com.lv.lesson9

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorCompletionService
import java.util.concurrent.Executors

/**
 * Date: 2017-05-11
 * Time: 13:48
 * Description:  有返回值的线程。Callable 与  Future ，ExecutorCompletionService 与  Future
 */
class CallableAndFuture {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            //单个有返回值的线程
            val executor = Executors.newSingleThreadExecutor()
            val future = executor.submit(Callable<String> {
                println("线程执行中")
                Thread.sleep(2000)
                return@Callable "asdasd"
            })
            println("这里是等待中")
            //这里有阻塞效果
            println("线程运行的结果是：${future.get()}")
            executor.shutdown()

            //多个有返回值的线程
            val threadPool = Executors.newFixedThreadPool(10)
            val completionService = ExecutorCompletionService<String>(threadPool)
            for (index in 0..9) {
                completionService.submit {
                    Thread.sleep((Math.random() * 5000).toLong())
                    println(Thread.currentThread().name)
                    return@submit "${Thread.currentThread().name} $index"
                }
            }
            for (index in 0..9) {
                println(completionService.take().get())
            }
            threadPool.shutdown()
            //多个有返回值的线程
            val threadPool2 = Executors.newFixedThreadPool(10)
            val callables = HashSet<Callable<String>>()
            callables.add(Callable {
                return@Callable "task1"
            })
            callables.add(Callable {
                return@Callable "task2"
            })
            callables.add(Callable {
                return@Callable "task3"
            })
            for (future1 in threadPool2.invokeAll(callables)) {
                println(future1.get())
            }
            threadPool2.shutdown()
        }
    }
}