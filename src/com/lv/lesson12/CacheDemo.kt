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

        }
    }

    fun getData(key: String): Any? {
        rwl.readLock().lock()
        var any = cache[key]
        if (any == null) {//第一个判断条件是将都锁改成写锁
            rwl.readLock().unlock()
            rwl.writeLock().lock()
            if (!cacheVild) {//是为了防止在后面写锁解除后重复写
                any = "aaaa" //这里可以设置成数据库查询
                cacheVild = true
            }
            rwl.writeLock().unlock()
            rwl.readLock().lock()
        }
        rwl.readLock().unlock()
        return any
    }

}
