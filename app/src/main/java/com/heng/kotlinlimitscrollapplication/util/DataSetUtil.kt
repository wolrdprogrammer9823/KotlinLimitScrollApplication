package com.heng.kotlinlimitscrollapplication.util
import com.heng.kotlinlimitscrollapplication.bean.IndexModel
import com.heng.kotlinlimitscrollapplication.widget.CharacterParser
import com.heng.kotlinlimitscrollapplication.widget.PinyinComparator
import java.util.*
import kotlin.collections.ArrayList

class DataSetUtil private constructor(){

    companion object{
        val instance : DataSetUtil by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DataSetUtil()
        }
    }

    fun createTab1Data(data: String): List<String> {

        val length = 51
        val listData = ArrayList<String>()

        for (i in 0.until(length)) {

            val data = data.plus(i + 1)
            listData.add(data)
        }

        return listData
    }

    fun loadIndexModelData(): List<IndexModel> {
        val indexModelList = ArrayList<IndexModel>()

        indexModelList.add(IndexModel("安阳"))
        indexModelList.add(IndexModel("鞍山"))
        indexModelList.add(IndexModel("保定"))
        indexModelList.add(IndexModel("包头"))
        indexModelList.add(IndexModel("北京"))
        indexModelList.add(IndexModel("河北"))
        indexModelList.add(IndexModel("北海"))
        indexModelList.add(IndexModel("安庆"))
        indexModelList.add(IndexModel("伊春"))
        indexModelList.add(IndexModel("宝鸡"))
        indexModelList.add(IndexModel("本兮"))
        indexModelList.add(IndexModel("滨州"))
        indexModelList.add(IndexModel("常州"))
        indexModelList.add(IndexModel("常德"))
        indexModelList.add(IndexModel("常熟"))
        indexModelList.add(IndexModel("枣庄"))
        indexModelList.add(IndexModel("内江"))
        indexModelList.add(IndexModel("阿坝州"))
        indexModelList.add(IndexModel("丽水"))
        indexModelList.add(IndexModel("成都"))
        indexModelList.add(IndexModel("承德"))
        indexModelList.add(IndexModel("沧州"))
        indexModelList.add(IndexModel("重庆"))
        indexModelList.add(IndexModel("东莞"))
        indexModelList.add(IndexModel("扬州"))
        indexModelList.add(IndexModel("甘南州"))
        indexModelList.add(IndexModel("大庆"))
        indexModelList.add(IndexModel("佛山"))
        indexModelList.add(IndexModel("广州"))
        indexModelList.add(IndexModel("合肥"))
        indexModelList.add(IndexModel("海口"))
        indexModelList.add(IndexModel("济南"))
        indexModelList.add(IndexModel("兰州"))
        indexModelList.add(IndexModel("南京"))
        indexModelList.add(IndexModel("泉州"))
        indexModelList.add(IndexModel("荣成"))
        indexModelList.add(IndexModel("三亚"))
        indexModelList.add(IndexModel("上海"))
        indexModelList.add(IndexModel("汕头"))
        indexModelList.add(IndexModel("天津"))
        indexModelList.add(IndexModel("武汉"))
        indexModelList.add(IndexModel("厦门"))
        indexModelList.add(IndexModel("宜宾"))
        indexModelList.add(IndexModel("张家界"))
        indexModelList.add(IndexModel("自贡"))

        val piyinComparator = PinyinComparator()
        for (indexModel in indexModelList) {
            indexModel.topCharacter =
                CharacterParser.instance.getSelling(indexModel.name).substring(0, 1)
                               .toUpperCase(Locale.ROOT)
            if (indexModel.name == "重庆") {
                indexModel.topCharacter = "C"
            }
        }
        Collections.sort(indexModelList, piyinComparator)
        return indexModelList
    }
}