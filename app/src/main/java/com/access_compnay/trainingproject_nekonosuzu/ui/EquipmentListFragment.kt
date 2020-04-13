package com.access_compnay.trainingproject_nekonosuzu.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import com.access_compnay.trainingproject_nekonosuzu.R
import com.access_compnay.trainingproject_nekonosuzu.data.Equipment
import com.access_compnay.trainingproject_nekonosuzu.viewmodel.EquipmentListViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.equipment_list_fragment.*
import kotlinx.android.synthetic.main.equipment_list_item.*

class EquipmentListFragment : Fragment() {
    private lateinit var viewModel: EquipmentListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // このFragmentのレイアウトを読み込む
        return inflater.inflate(R.layout.equipment_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // ViewModelインスタンスを生成/取得し、一覧に表示するリストをobserveする
        viewModel = ViewModelProviders.of(this).get(EquipmentListViewModel::class.java)
        viewModel.list.observe(this, Observer { list ->
            if (list != null) {
                listView.adapter = Adapter(view.context, list)
            }
        })

        // リストアイテムがクリックされたときの動作
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val data = viewModel.list.value?.get(position)
            if (data != null) {
                openDetail(data)
            }
        }

        // 検索用の入力フィールドのイベント処理をViewModelに任せる
        searchEditText.addTextChangedListener(viewModel.searchTextWatcher)
    }

    // 指定したアイテムで詳細画面のActivityを開く
    private fun openDetail(data: Equipment) {
        val openIntent = Intent(context, EquipmentDetailActivity::class.java).apply { putExtra(EquipmentDetailActivity.EXTRA_ID, data.id) }
        startActivity(openIntent)
    }

    // リストに表示するためのAdapter
    private class Adapter(val context: Context, val list: List<Equipment>) : BaseAdapter() {

        // InflateしたViewを保持し、指定されたEquipmentをViewにbindする
        private class ViewHolder(override val containerView: View?) : LayoutContainer {
            fun setup(eq: Equipment) {
                // TODO: レイアウトや表示の仕方を変更する？
                text1.text = "${eq.name}"
                text2.text = "${eq.os}"
                list_line.setBackgroundColor(Color.WHITE)
                if (eq.currentUserName != null) {
                    if (eq.currentUserName != "") {
                        text3.text = eq.currentUserName
                        list_line.setBackgroundColor(Color.parseColor("#d3d3d3"))
                    }else{
                        text3.text = ""
                    }
                }else{
                    text3.text = "null"
                }
            }
        }

        // あればtagから、なければ新たにインスタンス化してViewHolderを返す、拡張プロパティ
        private val View.viewHolder: ViewHolder
            get() = tag as? ViewHolder ?: ViewHolder(this)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.equipment_list_item, null)
            view.viewHolder.setup(getItem(position))
            return view
        }

        override fun getItem(position: Int): Equipment = list[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getCount(): Int = list.count()
    }
}