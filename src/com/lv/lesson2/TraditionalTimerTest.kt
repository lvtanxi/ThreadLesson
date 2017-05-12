package com.lv.lesson2

import java.util.*

/**
 * Date: 2017-05-10
 * Time: 15:20
 * Description: 传统的定时器
 */
class TraditionalTimerTest {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            Timer().schedule(object :TimerTask(){
                override fun run() {
                    println("普通炸弹")
                }
            },8000)
            Timer().schedule(object :TimerTask(){
                override fun run() {
                    println("连环炸弹")
                }
            },8000,3000)
            while (true){
                println(Date().seconds)
                Thread.sleep(1000)
            }
        }
    }
}