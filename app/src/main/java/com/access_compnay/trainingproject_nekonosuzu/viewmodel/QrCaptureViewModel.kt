package com.access_compnay.trainingproject_nekonosuzu.viewmodel
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.access_compnay.trainingproject_nekonosuzu.data.Equipment
import com.access_compnay.trainingproject_nekonosuzu.data.dataKodein
import com.access_compnay.trainingproject_nekonosuzu.ui.EquipmentDetailActivity
import org.kodein.di.generic.instance

/*
class QrCaptureViewModel(val equipmentId: String) : ViewModel() {
    val equipment: LiveData<Equipment> by dataKodein.instance(arg = equipmentId)

    private fun openDetail(data: Equipment) {
        val openIntent = Intent(context, EquipmentDetailActivity::class.java).apply { putExtra(EquipmentDetailActivity.EXTRA_ID, data.id) }
        startActivity(openIntent)
    }
}

class QrCaptureViewModelFactory(private val equipmentId: String) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EquipmentDetailViewModel(equipmentId) as T
    }
}
*/
