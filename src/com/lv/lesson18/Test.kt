package com.lv.lesson18

class Test(key: String, key2: String, private val value: String) : Thread() {

    private val testDo: TestDo = TestDo.instance
    private val key: String = key + key2


    override fun run() = synchronized(key::class){
        testDo.doSome(key, value)
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val a = Test("1", "", "1")
            val b = Test("1", "", "2")
            val c = Test("3", "", "3")
            val d = Test("4", "", "4")
            println("begin:" + System.currentTimeMillis() / 1000)
            a.start()
            b.start()
            c.start()
            d.start()

        }
    }
}

internal class TestDo private constructor() {

    fun doSome(key: Any, value: String) {

        // 以大括号内的是需要局部同步的代码，不能改动!
        run {
            try {
                Thread.sleep(1000)
                println(key.toString() + ":" + value + ":" + System.currentTimeMillis() / 1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        val instance = TestDo()
    }

}
