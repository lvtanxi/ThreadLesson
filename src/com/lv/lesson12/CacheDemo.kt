package com.lv.lesson12

import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * Date: 2017-05-11
 * Time: 15:15
 * Description: 模拟缓存系统,实现读写锁交换用
 */
class CacheDemo {

    private val cache = HashMap<String, Any>()

    private val rwl = ReentrantReadWriteLock()
    private @Volatile var cacheVild = false

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val cacheDemo = CacheDemo()
            for (index in 0..2){
                Thread{
                    val data = cacheDemo.getData("test")
                    println("${Thread.currentThread().name}获取的数据为$data")
                }.start()
            }
        }
    }

    fun getData(key: String): Any? {
        rwl.readLock().lock()
        var any = cache[key]
        if (any == null) {//第一个判断条件是将都锁改成写锁
            rwl.readLock().unlock()
            rwl.writeLock().lock()
            if (!cacheVild) {//是为了防止在后面写锁解除后重复写
                println("${Thread.currentThread().name} 加载一次数据.....")
                any = "aaaa" //这里可以设置成数据库查询
                cache.put(key,any)
                cacheVild = true
            }
            rwl.readLock().lock()
            rwl.writeLock().unlock()
        }
        rwl.readLock().unlock()
        return any
    }

}
