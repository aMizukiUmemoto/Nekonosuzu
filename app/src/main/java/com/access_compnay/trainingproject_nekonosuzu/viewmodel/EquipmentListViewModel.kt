package com.access_compnay.trainingproject_nekonosuzu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.text.Editable
import android.text.TextWatcher
import com.access_compnay.trainingproject_nekonosuzu.data.Equipment
import com.access_compnay.trainingproject_nekonosuzu.data.dataKodein
import org.kodein.di.generic.instance

class EquipmentListViewModel : ViewModel() {

    // dataKodeinより提供される、全Equipmentのリスト
    private val fullList: LiveData<List<Equipment>> by dataKodein.instance()

    // TextWatcherで監視している入力フィールドから入力される、検索用のテキスト
    private val filterText = MutableLiveData<String>()

    // 実際にViewに提供するリスト
    val list = MediatorLiveData<List<Equipment>>()

    init {
        // Viewに提供するリストは、fullListとfilterTextをソースとし、filter関数で加工する
        list.addSource(fullList) { filter() }
        list.addSource(filterText) { filter() }
    }

    // `fullList.value`と`filterText.value`が変更されたときに呼び出される
    private fun filter() {
        val currentFullList = fullList.value ?: emptyList()
        val currentFilterText = filterText.value ?: ""
        if (currentFullList.isNotEmpty() && currentFilterText.isNotBlank()) {
            // TODO: fullListとfilterTextが空じゃないなら、その2つから絞り込んで`list`を更新する
            val searchList = currentFullList.filter{
                it.name?.contains(currentFilterText,true) == true
                        || it.os?.contains(currentFilterText,true) == true
                        || it.currentUserName?.contains(currentFilterText,true) == true
            }
            list.value = searchList
        } else {
            // fullListとfilterTextのどちらかが空なら、絞り込む必要はないので、fullListをそのままlistとする
            list.value = fullList.value
        }
    }

    val searchTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            // 入力テキストが変更されたら、それをfilterTextに設定する
            filterText.value = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
    }
}
