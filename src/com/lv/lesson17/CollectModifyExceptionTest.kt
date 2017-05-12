package com.lv.lesson17

/**
 * Date: 2017-05-12
 * Time: 10:33
 * Description:
 */
class CollectModifyExceptionTest {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val arrayList = ArrayList<User>()
            arrayList.add(User("张三",15))
            arrayList.add(User("李四",16))
            arrayList.add(User("王五",17))
            arrayList.add(User("赵六",18))
            val iterator = arrayList.iterator()
            while (iterator.hasNext()){
                val user = iterator.next()
                if(user.name=="赵六")
                    iterator.remove()
                else
                    println(user)
            }
            val param=HashMap<String,String>()
            param.put("name","lv")
            for ((key,vale) in param)
                println(key+vale)
        }
    }
    data class User(val name:String,val age:Int)
}