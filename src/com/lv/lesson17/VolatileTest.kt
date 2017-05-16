package com.lv.lesson17

/**
 * Date: 2017-05-16
 * Time: 15:46
 * Description: 用volatile修饰的变量，线程在每次使用变量的时候，都会读取变量修改后的最的值。
 *                   volatile很容易被误用，用来进行原子性操作。注意的是添加了volatile并不一定能使线程
 *                   变得安全
 */
class VolatileTest(@Volatile var a: Int = 1, @Volatile var b: Int = 2) {
    fun change() {
        a = 3
        b = a
    }
    fun ouput() = println("a=$a,b=$b")

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            while (true){
                val volatileTest = VolatileTest()
                Thread{
                    Thread.sleep(10)
                    volatileTest.change()
                }.start()
                Thread{
                    Thread.sleep(10)
                    volatileTest.ouput()
                }.start()
            }
        }
    }

}