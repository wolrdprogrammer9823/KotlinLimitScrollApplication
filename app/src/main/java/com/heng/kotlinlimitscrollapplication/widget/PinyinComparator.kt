package com.heng.kotlinlimitscrollapplication.widget
import com.heng.kotlinlimitscrollapplication.bean.IndexModel

class PinyinComparator : Comparator<IndexModel> {

    override fun compare(o1: IndexModel?, o2: IndexModel?): Int {
        return if (o1?.topCharacter.equals("@") || o2?.topCharacter.equals("#")) {
            -1
        } else if (o1?.topCharacter.equals("#") || o2?.topCharacter.equals("@")) {
            1
        } else{
            o1?.topCharacter!!.compareTo(o2?.topCharacter!!)
        }
    }
}