package com.access_compnay.trainingproject_nekonosuzu.data.preset

import androidx.lifecycle.LiveData
import com.access_compnay.trainingproject_nekonosuzu.data.Equipment
import org.kodein.di.Kodein
import org.kodein.di.generic.*
import kotlin.math.absoluteValue

fun createPresetKodein() = Kodein {
    bind<LiveData<List<Equipment>>>() with provider { PresetEquipmentList() }
    bind<LiveData<Equipment>>() with factory { id: String ->
        println("id="+id)
        PresetEquipmentData(PresetEquipmentList.staticList[id.toInt()])
    }
}

// 固定の機種・バージョンのなかからランダムでリストを作る、Equipmentリストの実装

private val androidNames = arrayOf("Nexus 9", "Nexus 5", "arrows M03", "Xperia XZ1(SOV36)")
private val androidVersions = arrayOf("Android 5.0", "Android 5.1", "Android 6.0", "Android 7.0", "Android 7.1", "Android 8.0")
private val iosNames = arrayOf("iPhone 5", "iPhone 5s", "iPhone 6", "iPhone 7")
private val iosVersions = arrayOf("iOS 8.0.2", "iOS 8.3", "iOS 8.4.1", "iOS 9.2.1", "iOS 9.3.5", "iOS 10.0.2", "iOS 10.2", "iOS 10.3.3", "iOS 11.2.6", "iOS 11.3")

// 剰余を使うことで配列の範囲内から値を取得できるようにした、拡張関数
private fun Array<String>.getInBounds(number: Int): String {
    return this[number.absoluteValue % size]
}

private fun randomEquipment(index: Int, random: Int): Equipment {
    val isAndroid = random % 2 == 0
    val deviceNames = if (isAndroid) androidNames else iosNames
    val osVersions = if (isAndroid) androidVersions else iosVersions

    return Equipment().apply {
        id = index.toString()
        name = deviceNames.getInBounds(random)
        os = osVersions.getInBounds(random)
        if(index == 4){
            currentUserName = "梅本美月"
        }else if(index == 6){
            currentUserName = "Umemoto Mizuki"
        }else if(index == 7){
            currentUserName = "Umemoto Tokitokitokitoki Mizuki"
        }else{
            currentUserName = ""
        }
    }
}

class PresetEquipmentList : LiveData<List<Equipment>>() {
    override fun onActive() {
        this.value = staticList
        /*リスト一覧が表示された時*/
    }

    companion object {
        val staticList = createList()

        private fun createList(): List<Equipment> {
            val list = mutableListOf<Equipment>()
            val r = java.util.Random(System.currentTimeMillis())

            repeat(20) { index ->
                val e = randomEquipment(index, r.nextInt())
                list.add(e)
            }

            return list
        }
    }
}

class PresetEquipmentData(val data: Equipment) : LiveData<Equipment>() {
    override fun onActive() {
        this.value = data
    }
}
