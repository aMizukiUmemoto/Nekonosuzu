package com.access_compnay.trainingproject_nekonosuzu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.access_compnay.trainingproject_nekonosuzu.data.Equipment
import com.access_compnay.trainingproject_nekonosuzu.data.dataKodein
import org.kodein.di.generic.instance

class EquipmentDetailViewModel(val equipmentId: String) : ViewModel() {
    val equipment: LiveData<Equipment> by dataKodein.instance(arg = equipmentId)
}

class EquipmentDetailViewModelFactory(private val equipmentId: String) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EquipmentDetailViewModel(equipmentId) as T
    }
}
