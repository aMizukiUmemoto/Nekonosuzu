package com.access_compnay.trainingproject_nekonosuzu.data.firebase

import androidx.lifecycle.LiveData
import com.access_compnay.trainingproject_nekonosuzu.data.Equipment
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.provider

fun createFirebaseKodein() = Kodein {
    bind<LiveData<List<Equipment>>>() with provider { FirebaseEquipmentList() }
    bind<LiveData<Equipment>>() with factory { id: String -> FirebaseEquipmentData(id) }
}

class FirebaseEquipmentList : LiveData<List<Equipment>>() {
    override fun onActive() {
        //TODO
    }

    override fun onInactive() {
        //TODO
    }
}

class FirebaseEquipmentData(val id: String) : LiveData<Equipment>() {
    override fun onActive() {
        //TODO
    }

    override fun onInactive() {
        //TODO
    }
}
