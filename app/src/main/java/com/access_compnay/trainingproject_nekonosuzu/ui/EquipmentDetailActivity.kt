package com.access_compnay.trainingproject_nekonosuzu.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.access_compnay.trainingproject_nekonosuzu.R
import com.access_compnay.trainingproject_nekonosuzu.viewmodel.EquipmentDetailViewModel
import com.access_compnay.trainingproject_nekonosuzu.viewmodel.EquipmentDetailViewModelFactory
import kotlinx.android.synthetic.main.equipment_detail_activity.*

class EquipmentDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "id"
    }

    private lateinit var viewModel: EquipmentDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.equipment_detail_activity)

        val id = intent.getStringExtra(EXTRA_ID)
        viewModel = ViewModelProviders.of(this, EquipmentDetailViewModelFactory(id)).get(EquipmentDetailViewModel::class.java)

        viewModel.equipment.observe(this, Observer { data ->
            nameTextView.text = data?.name ?: ""
            osTextView.text = data?.os ?: ""
        })
    }
}